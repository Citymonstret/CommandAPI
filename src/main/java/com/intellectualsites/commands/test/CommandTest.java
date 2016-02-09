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
        CommandResult result = manager.handle(caller, "tlest banana 10");

        caller.message("Result: " + CommandHandlingOutput.nameField(result.getCommandResult()));
        try {
            caller.message("Input: " + result.getInput());
            caller.message("Caller: " + result.getCaller());
            if (result.getCommand() == null) {
                caller.message("Closets Match: " + result.getClosestMatch());
            } else {
                caller.message("Command: " + result.getCommand());
            }
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        // for (CommandManager command : CommandCloud.get('/')) {
        //    if (command instanceof Command) {
        //        caller.message("Found command: /" + ((Command) command).getCommand());
        //    } else {
        //        caller.message("Found manager: " + manager.getMeta("environment"));
        //    }
        // }
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
