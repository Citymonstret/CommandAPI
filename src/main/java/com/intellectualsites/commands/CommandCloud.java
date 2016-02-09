package com.intellectualsites.commands;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CommandCloud {

    private Character nullCharacter = '¤'; // Assuming this will never be used :P

    private final Map<Character, Collection<CommandManager>> commands;

    CommandCloud() {
        commands = new ConcurrentHashMap<Character, Collection<CommandManager>>();
    }

    protected Character filterCharacter(Character in) {
        return in == null ? nullCharacter : in;
    }

    protected void addCommand(CommandManager command) {
        Character character = filterCharacter(command.getInitialCharacter());
        Collection<CommandManager> collection;
        if (!commands.containsKey(character)) {
            commands.put(character, ((collection = new HashSet<CommandManager>())));
        } else {
            collection = commands.get(character);
        }
        collection.add(command);
    }

    protected Collection<CommandManager> getCommands(Character initialCharacter) {
        initialCharacter = filterCharacter(initialCharacter);
        if (!commands.containsKey(initialCharacter)) {
            return new HashSet<CommandManager>();
        }
        return commands.get(initialCharacter);
    }

    private static final CommandCloud instance = new CommandCloud();

    public static void add(final CommandManager commandManager) {
        instance.addCommand(commandManager);
    }

    public static Collection<CommandManager> get(final Character initialCharacter) {
        return instance.getCommands(initialCharacter);
    }
}
