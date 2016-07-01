package com.intellectualsites.commands.callers;
import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.CommandManager;
import com.intellectualsites.commands.parser.Parserable;

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
            Parserable parserable = (Parserable) o;
            builder.append(parserable.getName()).append(" (").append(parserable.getParser().getName()).append("; Example: ").append(parserable.getParser().getExample()).append("), ");
        }
        message(builder.substring(0, builder.length() - 2));
    }
}
