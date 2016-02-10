package com.intellectualsites.commands.bukkit.plugin;

import com.intellectualsites.commands.CommandHandlingOutput;
import com.intellectualsites.commands.CommandResult;
import com.intellectualsites.commands.bukkit.senders.ConsoleCaller;
import com.intellectualsites.commands.bukkit.senders.PlayerCaller;
import com.intellectualsites.commands.callers.CommandCaller;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class PluginCommand extends PluginCommandBody implements CommandExecutor {

    final public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        CommandCaller caller;
        if (commandSender instanceof ConsoleCommandSender) {
            caller = ConsoleCaller.instance;
        } else {
            caller = new PlayerCaller((Player) commandSender);
        }
        CommandResult result = handle(caller, strings);
        return result.getCommandResult() == CommandHandlingOutput.SUCCESS;
    }
}
