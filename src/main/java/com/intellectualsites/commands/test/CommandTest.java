package com.intellectualsites.commands.test;

import com.intellectualsites.commands.*;
import com.intellectualsites.commands.argument.ArgumentType;
import com.intellectualsites.commands.callers.CommandCaller;
import com.intellectualsites.commands.callers.SystemCaller;
import com.intellectualsites.commands.pagination.PaginatedCommand;
import com.intellectualsites.commands.pagination.PaginationFactory;
import com.intellectualsites.commands.permission.AdvancedPermission;

import java.util.ArrayList;
import java.util.List;

public class CommandTest {

    private static class Dog {

        private String name;

        public Dog(String name) {
            this.name = "dog::" + name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static void main(String[] args) {
        //
        // Create a command caller
        //
        CommandCaller caller = new SystemCaller();

        //
        // Let's create a manager
        //
        final CommandManager manager = new CommandManager();
        // Set some (optional) settings
        manager.getManagerOptions().setRequirePrefix(false).setUseAdvancedPermissions(true)
                .setPermissionChecker(new AdvancedPermission.PermissionChecker() {
                    public boolean isPermitted(CommandCaller caller, Command command) {
                        if (caller instanceof SystemCaller) {
                            return true;
                        }
                        return caller.hasAttachment(command.getPermission());
                    }
                });
        // Create a command from annotation (see the command below)
        if(!manager.createCommand(new TestCommand())) {
            System.out.println("Failed to create command :(");
        }
        // Let's test the "get closets match" system
        debug(caller, manager.handle(caller, "tlest banana 10"));
        // Let's try to call it
        debug(caller, manager.handle(caller, "HUNDIS test banana 10 Cows"));

        //
        // Test the pagination command type
        //
        final List<Dog> dogList = new ArrayList<Dog>();
        for (int i = 0; i < 50; i++) {
            dogList.add(new Dog(i + ""));
        }
        // Add it
        manager.addCommand(new PaginationTest(dogList));
        // Call it
        debug(caller, manager.handle(caller, "dogs 5"));

        // for (CommandManager command : CommandCloud.get('/')) {
        //    if (command instanceof Command) {
        //        caller.message("Found command: /" + ((Command) command).getCommand());
        //    } else {
        //        caller.message("Found manager: " + manager.getMeta("environment"));
        //    }
        // }
    }

    public static class PaginationTest extends PaginatedCommand<Dog> {

        public PaginationTest(List<Dog> items) {
            super(Dog.class, items, 10, "dogs", "/dogs [page]", "List dogs", "dogs.permission", new String[0], Object.class);
            withArgument("page", ArgumentType.Integer, "Dog page");
        }

        @Override
        public boolean handleTooBigPage(CommandInstance instance, int specifiedPage, int maxPages) {
            instance.getCaller().message("Page too big (" + specifiedPage + " < " + maxPages + ")");
            return true;
        }

        @Override
        public boolean onCommand(PaginatedCommandInstance instance) {
            for (int i = 0; i < instance.getPage().getItems().length; i++) {
                instance.getCaller().message("[" + (i+1) + "] " + instance.getPage().getItems()[i]);
            }
            return true;
        }
    }

    @CommandDeclaration(command = "test", usage = "/test [word]")
    public static class TestCommand extends Command {

        TestCommand() {
            withArgument("word", ArgumentType.String, "A word");
            withArgument("rest", ArgumentType.MultiString, "More strings");
            withContext("dog", DogType, "A dog name");
        }

        @Override
        public boolean onCommand(CommandInstance instance) {
            Dog context = instance.getValue("dog", Dog.class);
            if (context != null) {
                System.out.println("Context was set: " + context);
            } else {
                System.out.println("Context not specified");
            }

            System.out.println("The word is: " + instance.getString("word"));
            System.out.println("And the rest: " + instance.getString("rest"));
            return true;
        }
    }

    public static ArgumentType<Dog> DogType = new ArgumentType<Dog>("dog", new Dog("Test")) {
        @Override
        public Dog parse(String in) {
            return new Dog(in);
        }
    };

    private static void debug(CommandCaller caller, CommandResult result) {
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
    }
}
