package com.intellectualsites.commands.callers;
import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.CommandManager;
import com.intellectualsites.commands.argument.Argument;

import java.util.Collection;

public class SystemCaller implements CommandCaller {

    public boolean hasPermission(String permission) {
        return true;
    }

    public void message(String message) {
        System.out.println(message);
    }

    public Object getSuperCaller() {
        return new Object();
    }

    public boolean hasAttachment(String a) {
        return true;
    }

    public void sendRequiredArgumentsList(CommandManager manager, Command cmd, Collection required, String usage) {
        StringBuilder builder = new StringBuilder();
        builder.append(cmd.getCommand()).append(" requires ");
        for (Object o: required) {
            Argument argument = (Argument) o;
            builder.append(argument.getName()).append(" (").append(argument.getArgumentType().getName()).append("; Example: ").append(argument.getArgumentType().getExample()).append("), ");
        }
        message(builder.substring(0, builder.length() - 2));
    }
}
