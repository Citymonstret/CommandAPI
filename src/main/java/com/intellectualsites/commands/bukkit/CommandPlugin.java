package com.intellectualsites.commands.bukkit;

import com.intellectualsites.commands.bukkit.commands.TestBase;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class CommandPlugin extends JavaPlugin {

    private static CommandPlugin instance;

    public static CommandPlugin get() {
        return instance;
    }

    private final Map<String, String> stringCache = new HashMap<String, String>();

    public String getString(String key) {
        if (this.stringCache.containsKey(key)) {
            return stringCache.get(key);
        }
        if (!getConfig().contains("msg." + key)) {
            return "[NotFound:" + key + "]";
        }
        String val = getConfig().getString("msg." + key);
        this.stringCache.put(val, key);
        return val;
    }

    @Override
    public void onEnable() {
        instance = this;
        getCommand("test").setExecutor(new TestBase());

        getConfig().addDefault("not_permitted", ChatColor.RED + "You are not allowed to use that command");
        getConfig().addDefault("requires_player", ChatColor.RED + "You are not allowed to use that command");
        getConfig().addDefault("command_not_found", ChatColor.RED + "There is no such command");;
    }
}
