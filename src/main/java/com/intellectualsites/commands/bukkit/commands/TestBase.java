package com.intellectualsites.commands.bukkit.commands;

import com.intellectualsites.commands.CommandDeclaration;
import com.intellectualsites.commands.CommandInstance;
import com.intellectualsites.commands.argument.ArgumentType;
import com.intellectualsites.commands.bukkit.argument.Player;
import com.intellectualsites.commands.bukkit.plugin.PluginCommand;
import com.intellectualsites.commands.bukkit.plugin.PluginCommandBody;
import org.bukkit.ChatColor;

@CommandDeclaration(
        command = "test",
        usage = "/test [case]",
        description = "Test the command API"
)
public class TestBase extends PluginCommand {

    public TestBase() {
        withArgument("case", ArgumentType.String, "Test Case");
    }

    @Override
    public boolean onCommand(CommandInstance instance) {
        return true;
    }

    @CommandDeclaration(
            command = "player",
            usage = "/test msg [plr] [msg]"
    )
    private static class PlayerMessage extends PluginCommandBody {

        protected PlayerMessage() {
            withArgument("plr", new Player(), "Recipient");
            withArgument("msg", ArgumentType.MultiString, "Message");
        }

        @Override
        public boolean onCommand(CommandInstance instance) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) instance.getValue("plr", org.bukkit.entity.Player.class);
            if (player == null || !player.isOnline()) {
                // TODO: Send error message
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getString("msg")));
            }
            return true;
        }
    }
}
