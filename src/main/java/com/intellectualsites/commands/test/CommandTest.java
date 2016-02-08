package com.intellectualsites.commands.test;

import com.intellectualsites.commands.ArgumentType;
import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.CommandDeclaration;
import com.intellectualsites.commands.CommandManager;
import com.intellectualsites.commands.callers.CommandCaller;
import com.intellectualsites.commands.callers.SystemCaller;

import java.util.Map;

public class CommandTest {

    public static void main(String[] args) {
        CommandCaller caller = new SystemCaller();
        CommandManager manager = new CommandManager();
        if(!manager.createCommand(new TestCommand())) {
            System.out.println("Failed to create command :(");
        }
        manager.getManagerOptions().setRequirePrefix(false);
        manager.handle(caller, "test banana cow 16 pandas");
    }

    @CommandDeclaration(command = "test", usage = "/test [word]")
    public static class TestCommand extends Command {
        TestCommand() {
            {
                withArgument("fruit", ArgumentType.String, "A fruit!");
            }
            addCommand(new Command("banana", new String[0]) {
                {
                    withArgument("animal", ArgumentType.String, "An animal");
                }
                @Override
                public boolean onCommand(CommandCaller caller, String[] arguments, Map<String, Object> valueMapping) {
                    if (getCommands().isEmpty()) {
                        addCommand(new Command("cow") {
                            {
                                withArgument("fruit", ArgumentType.String, "Fruit");
                                withArgument("number", ArgumentType.Integer, "How many??");
                            }
                            @Override
                            public boolean onCommand(CommandCaller caller, String[] arguments, Map<String, Object> valueMapping) {
                                caller.message("I eat " + valueMapping.get("number") + " " + valueMapping.get("fruit"));
                                return true;
                            }
                        });
                    }
                    handle(caller, arguments);
                    return true;
                }
            });
        }

        @Override
        public boolean onCommand(CommandCaller caller, String[] arguments, Map<String, Object> valueMapping) {
            handle(caller, arguments);
            return true;
        }
    }
}
