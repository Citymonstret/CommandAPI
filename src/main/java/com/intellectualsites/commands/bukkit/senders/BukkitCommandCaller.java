package com.intellectualsites.commands.bukkit.senders;

import com.intellectualsites.commands.callers.CommandCaller;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public abstract class BukkitCommandCaller<T extends CommandSender> implements CommandCaller<T> {

    final public void message(String message) {
        if (message.isEmpty()) {
            return;
        }
        getSuperCaller().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    final public boolean hasAttachment(String a) {
        return getSuperCaller().hasPermission(a);
    }
}
