package com.intellectualsites.commands;

import com.intellectualsites.commands.callers.CommandCaller;
import com.intellectualsites.commands.parser.Parser;
import com.intellectualsites.commands.parser.Parserable;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Command extends CommandManager {

    private Class requiredType = Object.class;
    private String command, usage = "", description = "", permission = "";
    private String[] aliases = new String[0];

    private int orderIndex = Integer.MAX_VALUE;

    protected Map<Integer, String> order = new HashMap<>();
    protected Map<String, Parserable> requiredArguments = new LinkedHashMap<>();

    private Parserable context = null;

    public Command() {
        super(null, new ArrayList<Command>());
    }

    public Command(String command) {
        super(null, new ArrayList<Command>());
        this.command = command;
    }

    public Command(String command, String usage) {
        super(null, new ArrayList<Command>());
        this.command = command;
        this.usage = usage;
    }

    public Command(String command, String usage, String description) {
        super(null, new ArrayList<Command>());
        this.command = command;
        this.usage = usage;
        this.description = description;
    }

    public Command(String command, String usage, String description, String permission) {
        super(null, new ArrayList<Command>());
        this.command = command;
        this.usage = usage;
        this.description = description;
        this.permission = permission;
    }

    public Command(String command, String[] aliases, String usage) {
        super(null, new ArrayList<Command>());
        this.command = command;
        this.aliases = aliases;
        this.usage = usage;
    }

    public Command(String command, String[] aliases) {
        super(null, new ArrayList<Command>());
        this.command = command;
        this.aliases = aliases;
    }

    public Command(String command, String usage, String description, String permission, String[] aliases, Class requiredType) {
        super(null, new ArrayList<Command>());
        this.command = command;
        this.usage = usage;
        this.description = description;
        this.permission = permission;
        this.aliases = aliases;
        this.requiredType = requiredType;
    }

    final public Class getRequiredType() {
        return this.requiredType;
    }

    final protected void create() {
        Annotation annotation = getClass().getAnnotation(CommandDeclaration.class);
        if (annotation == null) {
            throw new RuntimeException("Command does not have a CommandDeclaration");
        }
        CommandDeclaration declaration = (CommandDeclaration) annotation;
        this.command = declaration.command();
        this.usage = declaration.usage();
        this.description = declaration.description();
        this.usage = declaration.usage();
        this.permission = declaration.permission();
        this.aliases = declaration.aliases();
        this.requiredType = declaration.requiredType();
    }

    @Override
    final public String toString() {
        return this.command;
    }
    @Override
    final public int hashCode() {
        return this.command.hashCode();
    }

    public boolean onCommand(CommandCaller caller, String[] arguments, Map<String, Object> valueMapping) {
        return this.onCommand(new CommandInstance(caller, arguments, valueMapping));
    }

    public boolean onCommand(CommandInstance instance) { return false; }

    public CommandResult handle(CommandCaller caller, String[] args) {
        if (args.length == 0) {
            return super.handle(caller, "");
        }
        StringBuilder builder = new StringBuilder();
        for (String s : args) {
            builder.append(s).append(" ");
        }
        String s = builder.substring(0, builder.length() - 1);
        return super.handle(caller, s);
    }

    final public String getCommand() {
        return this.command;
    }

    final public String getUsage() {
        return this.usage.isEmpty() ? command : usage;
    }

    final public String getPermission() {
        return this.permission.isEmpty() ? "rectangular." + this.command : this.permission;
    }

    final public String getDescription() {
        return this.description;
    }

    final public String[] getAliases() {
        return this.aliases;
    }

    final public Map<String, Parserable> getRequiredArguments() {
        return new HashMap<String, Parserable>(this.requiredArguments);
    }

    public boolean hasContext() {
        return this.context != null;
    }

    public Parserable getContext() {
        return this.context;
    }

    public <T> Command withContext(String name, Parser<T> type, String desc) {
        this.context = new Parserable<T>(name, type, desc);
        return this;
    }

    public <T> Command withArgument(String name, Parser<T> argumentType, String desc) {
        // Argument argument = new Argument<T>(name, argumentType, desc);
        Parserable parserable = new Parserable<T>(name, argumentType, desc);
        order.put(orderIndex--, name);
        requiredArguments.put(name, parserable);
        return this;
    }

    public <T> Command withArgument(Parserable<T> argument) {
        order.put(orderIndex--, argument.getName());
        requiredArguments.put(argument.getName(), argument);
        return this;
    }

    public Map<Integer,String> getOrder() {
        return order;
    }
}

