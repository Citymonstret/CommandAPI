package com.intellectualsites.commands.bukkit.messages;

import com.intellectualsites.commands.callers.CommandCaller;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class MessageProvider {
    public enum MessageKey {
        NOT_PERMITTED("You are not permitted to use this command"),
        REQUIRES_CONSOLE("This command can only be executed by the console"),
        REQUIRES_PLAYER("This command can only be executed by a player"),
        ERROR_OCCURRED("Something went wrong when executing the command"),
        COMMAND_NOT_FOUND("There is no such command"),
        CLOSETS_MATCH("Did you mean: /%match?"),
        USAGE("Command Usage: %usage");

        String defaultString;

        MessageKey(final String defaultString) {
            this.defaultString = defaultString;
        }
    }

    public static class Message {

        private ChatColor color;
        private String message;

        Message(String message, ChatColor color) {
            this.color = color;
            this.message = message;
        }

        public ChatColor getColor() {
            return this.color;
        }

        public String getRawMessage() {
            return this.message;
        }

        public String getMessage() {
            return this.color + this.message;
        }

        @Override
        public String toString() {
            return this.getMessage();
        }

        public void setColor(final ChatColor color) {
            this.color = color;
        }

        public void  setMessage(final String message) {
            this.message = message;
        }

        public void send(CommandCaller caller) {
            caller.message(toString());
        }

        public void send(CommandCaller caller, String s, String r) {
            caller.message(toString().replace(s, r));
        }
    }

    private Map<MessageKey, Message> messages = new HashMap<MessageKey, Message>();

    {
        for (MessageKey message : MessageKey.values()) {
            messages.put(message, new Message(message.defaultString, ChatColor.RED));
        }
    }

    public Message getMessage(MessageKey key) {
        return messages.get(key);
    }
}
