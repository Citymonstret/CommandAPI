package com.intellectualsites.commands.test;

import com.intellectualsites.commands.*;
import com.intellectualsites.commands.argument.ArgumentType;
import com.intellectualsites.commands.callers.CommandCaller;
import com.intellectualsites.commands.callers.SystemCaller;
import com.intellectualsites.commands.permission.AdvancedPermission;

public class CommandTest {

    public static void main(String[] args) {
        CommandCaller caller = new SystemCaller();
        CommandManager manager = new CommandManager();
        if(!manager.createCommand(new TestCommand())) {
            System.out.println("Failed to create command :(");
        }
        manager.getManagerOptions().setRequirePrefix(false).setUseAdvancedPermissions(true)
                .setPermissionChecker(new AdvancedPermission.PermissionChecker() {
                    public boolean isPermitted(CommandCaller caller, Command command) {
                        if (caller instanceof SystemCaller) {
                            return true;
                        }
                        return caller.hasAttachment(command.getPermission());
                    }
                });
        manager.handle(caller, "test banana 10");
    }

    @CommandDeclaration(command = "test", usage = "/test [word]")
    public static class TestCommand extends Command {

        TestCommand() {
            withArgument("word", ArgumentType.String, "A word");
            withArgument("number", ArgumentType.Integer, "A number");
        }

        @Override
        public boolean onCommand(CommandInstance instance) {
            System.out.println("The word is: " + instance.getString("word"));
            System.out.println("The number is: " + instance.getInteger("number"));
            return true;
        }
    }
}
