package com.intellectualsites.commands.argument;

import java.time.Instant;

public abstract class ArgumentType<T> {

    private final java.lang.String name;
    private final T example;

    public ArgumentType(java.lang.String name, T example) {
        this.name = name;
        this.example = example;
    }

    public abstract T parse(java.lang.String in);

    @Override
    public final java.lang.String toString() {
        return this.getName();
    }

    public final java.lang.String getName() {
        return this.name;
    }

    public final T getExample() {
        return this.example;
    }

    public static final ArgumentType<Integer> Integer = new ArgumentType<java.lang.Integer>("int", 16) {
        @Override
        public Integer parse(java.lang.String in) {
            Integer value = null;
            try {
                value = java.lang.Integer.parseInt(in);
            } catch(final Exception ignored) {
                ignored.printStackTrace();
            }
            return value;
        }
    };

    public static final ArgumentType<Boolean> Boolean = new ArgumentType<java.lang.Boolean>("boolean", true) {
        @Override
        public Boolean parse(java.lang.String in) {
            Boolean value = null;
            if (in.equalsIgnoreCase("true") || in.equalsIgnoreCase("Yes") || in.equalsIgnoreCase("1")) {
                value = true;
            } else if (in.equalsIgnoreCase("false") || in.equalsIgnoreCase("No") || in.equalsIgnoreCase("0")) {
                value = false;
            }
            return value;
        }
    };

    public static final MultiString MultiString = new MultiString();

    public static class MultiString extends ArgumentType<String> implements InstantArray {

        public MultiString() {
            super("strings", "Multiple words...");
        }

        @Override
        public String parse(String in) {
            return in;
        }
    }

    public static final ArgumentType<java.lang.String> String = new ArgumentType<java.lang.String>("string", "Example") {
        @Override
        public String parse(String in) {
            return in;
        }
    };

    public interface InstantArray {}
}
