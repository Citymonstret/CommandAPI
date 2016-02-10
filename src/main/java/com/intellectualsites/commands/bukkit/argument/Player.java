package com.intellectualsites.commands.bukkit.argument;

import com.intellectualsites.commands.argument.ArgumentType;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Player extends ArgumentType<org.bukkit.entity.Player> {

    public Player() {
        super("player", null);
    }

    @Override
    public org.bukkit.entity.Player parse(String in) {
        if (in.length() > 16) {
            return Bukkit.getPlayer(UUID.fromString(in));
        }
        return Bukkit.getPlayer(in);
    }
}
