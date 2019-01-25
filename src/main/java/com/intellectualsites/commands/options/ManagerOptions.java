package com.intellectualsites.commands.options;

import com.intellectualsites.commands.permission.AdvancedPermission;

@SuppressWarnings("unused") public class ManagerOptions {

    private boolean requireInitialCharacter, useAdvancedPermissions, registerToCloud,
        findCloseMatches, printStacktrace;
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

    @SuppressWarnings("UnusedReturnValue") public ManagerOptions setUsageFormat(String format) {
        this.usageFormat = format;
        return this;
    }

    public boolean getPrintStacktrace() {
        return this.printStacktrace;
    }

    public ManagerOptions setPrintStacktrace(boolean printStacktrace) {
        this.printStacktrace = printStacktrace;
        return this;
    }

    public boolean getFindCloseMatches() {
        return this.findCloseMatches;
    }

    public boolean getRegisterToCloud() {
        return this.registerToCloud;
    }

    public ManagerOptions setRegisterToCloud(boolean registerToCloud) {
        this.registerToCloud = true;
        return this;
    }

    public boolean getRequirePrefix() {
        return this.requireInitialCharacter;
    }

    public ManagerOptions setRequirePrefix(boolean requirePrefix) {
        this.requireInitialCharacter = requirePrefix;
        return this;
    }

    public boolean getUseAdvancedPermissions() {
        return this.useAdvancedPermissions;
    }

    public ManagerOptions setUseAdvancedPermissions(boolean useAdvancedPermissions) {
        this.useAdvancedPermissions = useAdvancedPermissions;
        return this;
    }

    public AdvancedPermission.PermissionChecker getPermissionChecker() {
        return this.permissionChecker;
    }

    public ManagerOptions setPermissionChecker(AdvancedPermission.PermissionChecker checker) {
        this.permissionChecker = checker;
        return this;
    }

    public ManagerOptions getFindCloseMatches(boolean findCloseMatches) {
        this.findCloseMatches = findCloseMatches;
        return this;
    }
}
