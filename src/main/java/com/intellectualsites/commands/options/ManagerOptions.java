package com.intellectualsites.commands.options;

import com.intellectualsites.commands.permission.AdvancedPermission;

public class ManagerOptions {

    private boolean requireInitialCharacter, useAdvancedPermissions;
    private AdvancedPermission.PermissionChecker permissionChecker;

    public ManagerOptions() {
        this.requireInitialCharacter = true;
        this.permissionChecker = null;
        this.useAdvancedPermissions = false;
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