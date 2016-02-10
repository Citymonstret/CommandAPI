package com.intellectualsites.commands.bukkit.plugin;

import com.intellectualsites.commands.Command;

import com.intellectualsites.commands.CommandResult;
import com.intellectualsites.commands.bukkit.messages.MessageProvider;
import com.intellectualsites.commands.callers.CommandCaller;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;

import static com.intellectualsites.commands.CommandHandlingOutput.*;
import static com.intellectualsites.commands.CommandHandlingOutput.WRONG_USAGE;

public abstract class PluginCommandBody extends Command {

    private final MessageProvider messageProvider;

    {
        messageProvider = new MessageProvider();

        getManagerOptions().setPrintStacktrace(false);
        getManagerOptions().setUseAdvancedPermissions(false);
        getManagerOptions().setRequirePrefix(false);
        getManagerOptions().setUsageFormat(""); // Do not send internal type
    }

    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }

    @Override
    public CommandResult handle(CommandCaller commandSender, String[] args) {
        CommandResult result = super.handle(commandSender, args);
        switch (result.getCommandResult()) {
            case NOT_PERMITTED:
                getMessageProvider()
                        .getMessage(MessageProvider.MessageKey.NOT_PERMITTED)
                        .send(commandSender);
                break;
            case CALLER_OF_WRONG_TYPE:
                if (commandSender.getSuperCaller() instanceof Player) {
                    getMessageProvider()
                            .getMessage(MessageProvider.MessageKey.REQUIRES_CONSOLE)
                            .send(commandSender);
                } else {
                    getMessageProvider()
                            .getMessage(MessageProvider.MessageKey.REQUIRES_PLAYER)
                            .send(commandSender);
                }
                break;
            case ERROR:
                getMessageProvider()
                        .getMessage(MessageProvider.MessageKey.ERROR_OCCURRED)
                        .send(commandSender);
                new CommandException("Something went wrong when executing the command", result.getStacktrace()).printStackTrace();
                break;
            case NOT_FOUND:
                if ( result.getClosestMatch() != null) {
                    getMessageProvider()
                            .getMessage(MessageProvider.MessageKey.CLOSETS_MATCH)
                            .send(commandSender, "%match", result.getClosestMatch().getCommand());
                } else {
                    getMessageProvider()
                            .getMessage(MessageProvider.MessageKey.COMMAND_NOT_FOUND)
                            .send(commandSender);
                }
                break;
            case WRONG_USAGE:
                getMessageProvider()
                        .getMessage(MessageProvider.MessageKey.USAGE)
                        .send(commandSender, "%usage", result.getCommand().getUsage());
                break;
            default:
                // Unknown type
                break;
        }
        return result;
    }
}
