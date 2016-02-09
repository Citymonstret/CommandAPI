package com.intellectualsites.commands.argument;

public abstract class ArgumentType<T> {

    private final String name;
    private final T example;

    public ArgumentType(String name, T example) {
        this.name = name;
        this.example = example;
    }

    public abstract T parse(String in);

    @Override
    public final String toString() {
        return this.getName();
    }

    public final String getName() {
        return this.name;
    }

    public final T getExample() {
        return this.example;
    }

    public static final ArgumentType<Integer> Integer = new ArgumentType<java.lang.Integer>("int", 16) {
        @Override
        public Integer parse(String in) {
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
        public Boolean parse(String in) {
            Boolean value = null;
            if (in.equalsIgnoreCase("true") || in.equalsIgnoreCase("Yes") || in.equalsIgnoreCase("1")) {
                value = true;
            } else if (in.equalsIgnoreCase("false") || in.equalsIgnoreCase("No") || in.equalsIgnoreCase("0")) {
                value = false;
            }
            return value;
        }
    };

    public static final ArgumentType<String> String = new ArgumentType<java.lang.String>("String", "Example") {
        @Override
        public String parse(String in) {
            return in;
        }
    };
}
