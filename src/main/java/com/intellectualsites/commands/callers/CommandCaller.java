package com.intellectualsites.commands.callers;

import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.CommandManager;
import com.intellectualsites.commands.argument.Argument;

import java.util.Collection;

public interface CommandCaller<T> {

    void message(String message);

    T getSuperCaller();

    boolean hasAttachment(String a);

    void sendRequiredArgumentsList(CommandManager manager, Command cmd, Collection<Argument> required, String usage);
}
