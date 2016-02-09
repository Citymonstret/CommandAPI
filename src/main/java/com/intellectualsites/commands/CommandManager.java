package com.intellectualsites.commands;

import com.intellectualsites.commands.callers.CommandCaller;
import com.intellectualsites.commands.options.ManagerOptions;
import com.intellectualsites.commands.permission.AdvancedPermission;
import com.intellectualsites.commands.permission.SimplePermission;
import com.intellectualsites.commands.argument.Argument;
import com.intellectualsites.commands.util.StringUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This manages all commands, and
 * as such; it is the manager. It
 * should be simple enough to understand
 *
 * @author Sauilitired
 */
public class CommandManager {

    private final ManagerOptions managerOptions;
    private final Map<String, Command> commands;
    private final Map<String, String> aliasMapping;
    private final Character initialCharacter;

    /**
     * Default constructor,
     * will use "/" as the
     * command prefix
     */
    public CommandManager() {
        this('/', new ArrayList<Command>());
    }

    /**
     * Constructor which allows you
     * to specify the command
     * prefix, without reequiring
     * a pre made list of commands
     * @param initialCharacter Command prefix
     */
    public CommandManager(Character initialCharacter) {
        this(initialCharacter, null);
    }

    /**
     * Constructor
     * @param initialCharacter Command prefix
     * @param commands Pre made list of commands
     */
    public CommandManager(Character initialCharacter, List<Command> commands) {
        this.managerOptions = new ManagerOptions();
        this.commands = new ConcurrentHashMap<String, Command>();
        this.aliasMapping = new ConcurrentHashMap<String, String>();
        this.initialCharacter = initialCharacter;
        if (commands != null) {
            for (Command command : commands) {
                addCommand(command);
            }
        }
    }

    final public void addCommand(final Command command) {
        this.commands.put(command.getCommand().toLowerCase(), command);
        for (String alias : command.getAliases()) {
            aliasMapping.put(alias.toLowerCase(), command.getCommand().toLowerCase());
        }
    }

    final public boolean createCommand(final Command command) {
        try {
            command.create();
        } catch(final Exception e) {
            e.printStackTrace();
            return false;
        }
        if (command.getCommand() != null) {
            this.addCommand(command);
            return true;
        }
        return false;
    }

    final public Collection<Command> getCommands() {
        return this.commands.values();
    }

    final public int handle(CommandCaller caller, String input) {
        // If we want to use the prefix, then this will make sure it's there
        // Could be used to separate commands from chat, etc
        if (managerOptions.getRequirePrefix() && initialCharacter != null && !StringUtil.startsWith(initialCharacter, input)) {
            return CommandHandlingOutput.NOT_COMMAND;
        }
        // If there is an initial character set, and it's present - remove it
        if (initialCharacter != null && StringUtil.startsWith(initialCharacter, input)) {
            input = StringUtil.replaceFirst(initialCharacter, input);
        }
        // Now let's split the command up
        // into labels and arguments
        String[] parts = input.split(" ");
        String[] args;
        String command = parts[0].toLowerCase();
        if (parts.length == 1) {
            args = new String[0];
        } else {
            args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);
        }
        // Let's fetch the command
        Command cmd = null;
        if (commands.containsKey(command)) {
            cmd = commands.get(command);
        } else if (aliasMapping.containsKey(command)) {
            cmd = commands.get(aliasMapping.get(command));
        }
        if (cmd == null) {
            return CommandHandlingOutput.NOT_FOUND;
        }
        if (!cmd.getRequiredType().isInstance(caller.getSuperCaller())) {
            return CommandHandlingOutput.CALLER_OF_WRONG_TYPE;
        }
        // if (!caller.hasPermission(cmd.getPermission())) {
        //     return CommandHandlingOutput.NOT_PERMITTED;
        // }
        boolean permitted = true;
        if (managerOptions.getUseAdvancedPermissions()) {
            permitted = AdvancedPermission.getAdvancedPermission(cmd, managerOptions.getPermissionChecker()).isPermitted(caller);
        } else {
            permitted = SimplePermission.getSimplePermission(cmd).isPermitted(caller);
        }
        if (!permitted) {
            return CommandHandlingOutput.NOT_PERMITTED;
        }
        // Now the fun stuff is beginning :D
        Map<String, Argument> requiredArguments = cmd.getRequiredArguments();
        Map<String, Object> valueMapping = new HashMap<String, Object>();
        if (requiredArguments.size() > 0) {
            boolean success = true;
            if (args.length < requiredArguments.size()) {
                success = false;
            } else {
                int index = 0;
                for (Map.Entry<String, Argument> requiredArgument : requiredArguments.entrySet()) {
                    Object value = requiredArgument.getValue().parse(args[index++]);
                    if (value == null) {
                        success = false;
                        break;
                    } else {
                        valueMapping.put(requiredArgument.getValue().getName(), value);
                    }
                }
            }
            if (!success) {
                caller.sendRequiredArgumentsList(this, cmd, requiredArguments.values(), cmd.getUsage());
                return CommandHandlingOutput.WRONG_USAGE;
            }
        }
        try {
            boolean a = cmd.onCommand(caller, args, valueMapping);
            if (!a) {
                String usage = cmd.getUsage();
                if (usage != null && !usage.isEmpty()) {
                    caller.message(usage);
                }
                return CommandHandlingOutput.WRONG_USAGE;
            }
        } catch(final Throwable t) {
            t.printStackTrace();
            return CommandHandlingOutput.ERROR;
        }
        return CommandHandlingOutput.SUCCESS;
    }

    final public ManagerOptions getManagerOptions() {
        return this.managerOptions;
    }

    final public char getInitialCharacter() {
        return this.initialCharacter;
    }
}
