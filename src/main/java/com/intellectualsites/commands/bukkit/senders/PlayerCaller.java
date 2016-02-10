package com.intellectualsites.commands.bukkit.senders;

import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.CommandManager;
import com.intellectualsites.commands.argument.Argument;
import org.bukkit.entity.Player;

import java.util.Collection;

public class PlayerCaller extends BukkitCommandCaller<Player> {

    private final Player player;

    public PlayerCaller(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Player getSuperCaller() {
        return this.player;
    }

    public void sendRequiredArgumentsList(CommandManager manager, Command cmd, Collection<Argument> required, String usage) {
        // TODO Implement
    }
}