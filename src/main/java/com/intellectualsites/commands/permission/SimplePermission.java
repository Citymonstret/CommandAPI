package com.intellectualsites.commands.permission;

import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.callers.CommandCaller;

public class SimplePermission extends Permission {

    public SimplePermission(String internalKey) {
        super(internalKey);
    }

    @Override
    public boolean isPermitted(CommandCaller caller) {
        return caller.hasAttachment(toString());
    }

    public static SimplePermission getSimplePermission(Command command) {
        if (Permission.internalMap.containsKey(command.getPermission())) {
            return (SimplePermission) Permission.internalMap.get(command.getPermission());
        }
        return new SimplePermission(command.getPermission());
    }
}
