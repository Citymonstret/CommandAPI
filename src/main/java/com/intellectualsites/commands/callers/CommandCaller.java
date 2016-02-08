package com.intellectualsites.commands.callers;

import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.CommandManager;
import com.intellectualsites.commands.util.Argument;

import java.util.Collection;

public interface CommandCaller<T> {

    boolean hasPermission(String permission);

    void message(String message);

    T getSuperCaller();

    void sendRequiredArgumentsList(CommandManager manager, Command cmd, Collection<Argument> required, String usage);
}
