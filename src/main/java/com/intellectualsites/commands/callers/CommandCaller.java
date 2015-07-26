package com.intellectualsites.commands.callers;

import com.intellectualsites.commands.Argument;
import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.CommandManager;

public interface CommandCaller<T> {

    boolean hasPermission(String permission);

    void message(String message);

    T getSuperCaller();

    void sendRequiredArgumentsList(CommandManager manager, Command cmd, Argument[] required);
}
