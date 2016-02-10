package com.intellectualsites.commands.bukkit.plugin;

import com.intellectualsites.commands.Command;

import com.intellectualsites.commands.CommandResult;
import com.intellectualsites.commands.bukkit.CommandPlugin;
import com.intellectualsites.commands.callers.CommandCaller;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;

import static com.intellectualsites.commands.CommandHandlingOutput.*;
import static com.intellectualsites.commands.CommandHandlingOutput.WRONG_USAGE;

public abstract class PluginCommandBody extends Command {

    {
        getManagerOptions().setPrintStacktrace(false);
        getManagerOptions().setUseAdvancedPermissions(false);
        getManagerOptions().setRequirePrefix(false);
        getManagerOptions().setUsageFormat(""); // Do not send internal type
    }

    @Override
    public CommandResult handle(CommandCaller commandSender, String[] args) {
        CommandResult result = super.handle(commandSender, args);
        switch (result.getCommandResult()) {
            case NOT_PERMITTED:
                sendString(commandSender, "not_permitted");
                break;
            case CALLER_OF_WRONG_TYPE:
                if (commandSender.getSuperCaller() instanceof Player) {
                    sendString(commandSender, "requires_console");
                } else {
                    sendString(commandSender, "requires_player");
                }
                break;
            case ERROR:
                sendString(commandSender, "error_occurred");
                new CommandException("Something went wrong when executing the command", result.getStacktrace()).printStackTrace();
                break;
            case NOT_FOUND:
                if ( result.getClosestMatch() != null) {
                    sendCloseMatch(commandSender, result.getClosestMatch());
                } else {
                    sendString(commandSender, "command_not_found");
                }
                break;
            case WRONG_USAGE:
                sendUsage(commandSender, result.getCommand().getUsage());
                break;
            default:
                // Unknown type
                break;
        }
        return result;
    }

    private void sendUsage(CommandCaller sender, String usage) {
        sender.message(CommandPlugin.get().getString("usage").replace("%usage", usage));
    }

    private void sendString(CommandCaller sender, String s) {
        sender.message(CommandPlugin.get().getString(s));
    }

    private void sendCloseMatch(CommandCaller sender, Command closeMatch) {
        sender.message(CommandPlugin.get().getString("closest_match").replace("%match", closeMatch.getCommand()));
    }
}
