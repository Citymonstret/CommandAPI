package com.intellectualsites.commands.permission;

import com.intellectualsites.commands.callers.CommandCaller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Permission {

    public static Map<String, Permission> internalMap = new ConcurrentHashMap<String, Permission>();

    private final String internalKey;

    public Permission(String internalKey) {
        this.internalKey = internalKey;
        internalMap.put(internalKey, this);
    }

    @Override
    public String toString() {
        return this.internalKey;
    }

    public abstract boolean isPermitted(CommandCaller caller);
}
