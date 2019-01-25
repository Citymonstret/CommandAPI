package com.intellectualsites.commands.permission;

import com.intellectualsites.commands.callers.CommandCaller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Permission {

    static final Map<String, Permission> internalMap = new ConcurrentHashMap<>();

    private final String internalKey;

    Permission(String internalKey) {
        this.internalKey = internalKey;
        internalMap.put(internalKey, this);
    }

    @Override public String toString() {
        return this.internalKey;
    }

    public abstract boolean isPermitted(CommandCaller caller);
}
