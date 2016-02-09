package com.intellectualsites.commands;

import com.intellectualsites.commands.callers.CommandCaller;
import com.intellectualsites.commands.util.StringComparison;

public class CommandResult {

    private final Command command;
    private final CommandManager manager;
    private final CommandCaller caller;
    private Command closestMatch;
    private Throwable throwable;
    private final int commandResult;
    private String input;

    protected CommandResult(String input, CommandManager commandManager, Command command, CommandCaller caller, int commandResult, Throwable throwable) {
        this.command = command;
        this.caller = caller;
        this.manager = commandManager;
        this.commandResult = commandResult;
        this.input = input;

        if (commandManager.getManagerOptions().getFindCloseMatches()) {
            if (commandResult == CommandHandlingOutput.NOT_FOUND) {
                closestMatch = new StringComparison<Command>(input, commandManager.getCommands().toArray(new Command[0])).getMatchObject();
            }
        }

        if (closestMatch == null) {
            if (command != null) {
                closestMatch = command;
            }
        }

        if (throwable != null && manager.getManagerOptions().getPrintStacktrace()) {
            throwable.printStackTrace();
        }
    }

    public Throwable getStacktrace() {
        return this.throwable;
    }

    public int getCommandResult() {
        return this.commandResult;
    }

    public CommandCaller getCaller() {
        return this.caller;
    }

    public CommandManager getManager() {
        return this.manager;
    }

    public Command getCommand() {
        return this.command;
    }

    public Command getClosestMatch() {
        return this.closestMatch;
    }

    public String getInput() {
        return input;
    }

    protected static class CommandResultBuilder {

        private int commandResult = CommandHandlingOutput.OUTPUT_BUILD_FAILURE;
        private CommandCaller caller = null;
        private CommandManager manager = null;
        private Command command = null;
        private String input = "";
        private Throwable throwable = null;

        protected CommandResultBuilder(){}

        public void setStacktrace(Throwable throwable) {
            this.throwable = throwable;
        }

        public void setCommandResult(int commandResult) {
            this.commandResult = commandResult;
        }

        public void setCommand(Command command) {
            this.command = command;
        }

        public void  setCaller(CommandCaller caller) {
            this.caller = caller;
        }

        public void setManager(CommandManager manager) {
            this.manager = manager;
        }

        public void setInput(String string) {
            this.input = string;
        }

        public CommandResult build() {
            return new CommandResult(input, manager, command, caller, commandResult, throwable);
        }
    }
}
