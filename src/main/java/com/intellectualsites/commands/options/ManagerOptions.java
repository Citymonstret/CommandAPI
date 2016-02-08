package com.intellectualsites.commands.options;

public class ManagerOptions {

    private boolean requireInitialCharacter;

    public ManagerOptions() {
        this.requireInitialCharacter = true;
    }

    public boolean getRequirePrefix() {
        return this.requireInitialCharacter;
    }

    public ManagerOptions setRequirePrefix(boolean requirePrefix) {
        this.requireInitialCharacter = requirePrefix;
        return this;
    }
}
