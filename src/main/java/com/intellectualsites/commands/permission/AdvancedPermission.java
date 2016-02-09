package com.intellectualsites.commands.permission;

import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.callers.CommandCaller;

public class AdvancedPermission extends Permission {

    private Command command;
    private PermissionChecker permissionChecker;

    public AdvancedPermission(Command command, PermissionChecker checker) {
        super(command.getCommand());

        this.command = command;
        this.permissionChecker = checker;
    }

    @Override
    public boolean isPermitted(CommandCaller caller) {
        return permissionChecker.isPermitted(caller, command);
    }

    public interface PermissionChecker {

        boolean isPermitted(CommandCaller caller, Command command);

    }

    public static AdvancedPermission getAdvancedPermission(Command command, PermissionChecker checker) {
        if (Permission.internalMap.containsKey(command.getCommand())) {
            return (AdvancedPermission) Permission.internalMap.get(command.getCommand());
        }
        return new AdvancedPermission(command, checker);
    }
}
