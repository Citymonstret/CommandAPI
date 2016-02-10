package com.intellectualsites.commands.options;

import com.intellectualsites.commands.permission.AdvancedPermission;

public class ManagerOptions {

    private boolean requireInitialCharacter, useAdvancedPermissions,
            registerToCloud, findCloseMatches, printStacktrace;
    private AdvancedPermission.PermissionChecker permissionChecker;
    private String usageFormat;

    public ManagerOptions() {
        this.requireInitialCharacter = true;
        this.permissionChecker = null;
        this.useAdvancedPermissions = false;
        this.findCloseMatches = true;
        this.registerToCloud = true;
        this.printStacktrace = true;
        this.usageFormat = "Command Usage: %usage";
    }

    public String getUsageFormat() {
        return this.usageFormat;
    }

    public boolean getPrintStacktrace() {
        return this.printStacktrace;
    }

    public boolean getFindCloseMatches() {
        return this.findCloseMatches;
    }

    public boolean getRegisterToCloud() {
        return this.registerToCloud;
    }

    public boolean getRequirePrefix() {
        return this.requireInitialCharacter;
    }

    public boolean getUseAdvancedPermissions() {
        return this.useAdvancedPermissions;
    }

    public AdvancedPermission.PermissionChecker getPermissionChecker() {
        return this.permissionChecker;
    }

    public ManagerOptions setUsageFormat(String format) {
        this.usageFormat = format;
        return this;
    }

    public ManagerOptions setPrintStacktrace(boolean printStacktrace) {
        this.printStacktrace = printStacktrace;
        return this;
    }

    public ManagerOptions setRegisterToCloud(boolean registerToCloud) {
        this.registerToCloud = true;
        return this;
    }

    public ManagerOptions getFindCloseMatches(boolean findCloseMatches) {
        this.findCloseMatches = findCloseMatches;
        return this;
    }

    public ManagerOptions setUseAdvancedPermissions(boolean useAdvancedPermissions) {
        this.useAdvancedPermissions = useAdvancedPermissions;
        return this;
    }

    public ManagerOptions setPermissionChecker(AdvancedPermission.PermissionChecker checker) {
        this.permissionChecker = checker;
        return this;
    }

    public ManagerOptions setRequirePrefix(boolean requirePrefix) {
        this.requireInitialCharacter = requirePrefix;
        return this;
    }
}
