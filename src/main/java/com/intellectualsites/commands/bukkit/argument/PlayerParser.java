package com.intellectualsites.commands.bukkit.argument;

import com.intellectualsites.commands.parser.Parser;
import com.intellectualsites.commands.parser.ParserResult;
import org.bukkit.Bukkit;

import java.util.UUID;

public class PlayerParser extends Parser<org.bukkit.entity.Player> {

    public PlayerParser() {
        super("player", null);
    }

    @Override
    public ParserResult<org.bukkit.entity.Player> parse(String in) {
        final org.bukkit.entity.Player player;
        if (in.length() > 16) {
            player = Bukkit.getPlayer(UUID.fromString(in));
        } else {
            player = Bukkit.getPlayer(in);
        }
        if (player == null) {
            return new ParserResult<>("Couldn't find the player");
        } else {
            return new ParserResult<>(player);
        }
    }
}
