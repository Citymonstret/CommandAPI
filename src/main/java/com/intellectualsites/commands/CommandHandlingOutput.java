package com.intellectualsites.commands;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the output, that you can
 * fetch from the command manager
 */
public class CommandHandlingOutput {

    /**
     * The caller was of the wrong type
     */
    public static int CALLER_OF_WRONG_TYPE = -6;

    /**
     * Not a command (filtered out)
     */
    public static int NOT_COMMAND = -5;

    /**
     * There is no such command
     */
    public static int NOT_FOUND = -4;

    /**
     * The caller isn't permitted to call
     * the command
     */
    public static int NOT_PERMITTED = -3;

    /**
     * Something went wrong
     */
    public static int ERROR = -2;

    /**
     * Incorrect command usage
     */
    public static int WRONG_USAGE = -1;

    /**
     * Everything went well
     */
    public static int SUCCESS = 1;

    private static Map<Integer, String> nameCache = new HashMap<Integer, String>();

    /**
     * Get the name of the field corresponding
     * to the result code (such as WRONG_USAGE or SUCCESS)
     *
     * @param code Result Code
     * @return Name | null??
     */
    public static String nameField(int code) {
        if (nameCache.containsKey(code)) {
            return nameCache.get(code);
        }
        Field[] fields = CommandHandlingOutput.class.getDeclaredFields();
        for (final Field field : fields) {
            if (field.getGenericType() == Integer.TYPE) {
                try {
                    if ((Integer) field.get(CommandHandlingOutput.class) == code) {
                        nameCache.put(code, field.getName());
                        return field.getName();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return "null??";
    }
}
