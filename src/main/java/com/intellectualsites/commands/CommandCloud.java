package com.intellectualsites.commands;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings({"unused", "WeakerAccess"}) public class CommandCloud {

    private static final CommandCloud instance = new CommandCloud();
    private final Map<Character, Collection<CommandManager>> commands;

    CommandCloud() {
        commands = new ConcurrentHashMap<>();
    }

    public static void add(final CommandManager commandManager) {
        instance.addCommand(commandManager);
    }

    public static Collection<CommandManager> get(final Character initialCharacter) {
        return instance.getCommands(initialCharacter);
    }

    protected Character filterCharacter(Character in) {
        // Assuming this will never be used :P
        char nullCharacter = '¤';
        return in == null ? nullCharacter : in;
    }

    protected void addCommand(CommandManager command) {
        Character character = filterCharacter(command.getInitialCharacter());
        Collection<CommandManager> collection;
        if (!commands.containsKey(character)) {
            commands.put(character, ((collection = new HashSet<>())));
        } else {
            collection = commands.get(character);
        }
        collection.add(command);
    }

    protected Collection<CommandManager> getCommands(Character initialCharacter) {
        initialCharacter = filterCharacter(initialCharacter);
        if (!commands.containsKey(initialCharacter)) {
            return new HashSet<>();
        }
        return commands.get(initialCharacter);
    }
}
