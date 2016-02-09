package com.intellectualsites.commands;

import com.intellectualsites.commands.callers.CommandCaller;

import java.util.Map;

public class CommandInstance {

    protected final CommandCaller caller;
    protected final String[] arguments;
    protected final Map<String, Object> valueMapping;

    public CommandInstance(final CommandCaller caller, final String[] arguments, final Map<String, Object> valueMapping) {
        this.caller = caller;
        this.arguments = arguments;
        this.valueMapping = valueMapping;
    }

    public CommandCaller getCaller() {
        return this.caller;
    }

    public String[] getArguments() {
        return this.arguments;
    }

    public Map<String, Object> getValueMapping() {
        return this.valueMapping;
    }

    public <T> T getValue(String key, Class<? extends T> clazz) {
        if (!this.valueMapping.containsKey(key)) {
            return null;
        }
        return clazz.cast(this.valueMapping.get(key));
    }

    public int getInteger(String key) {
        return getValue(key, Integer.class);
    }

    public String getString(String key) {
        return getValue(key, String.class);
    }

    public Object getValue(String key) {
        if (!this.valueMapping.containsKey(key)) {
            return null;
        }
        return this.valueMapping.get(key);
    }
}
