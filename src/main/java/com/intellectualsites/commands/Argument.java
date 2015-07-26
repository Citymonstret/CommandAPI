package com.intellectualsites.commands;

public abstract class Argument<T> {

    private final String name;
    private final T example;

    public Argument(String name, T example) {
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

    public static final Argument<Integer> Integer = new Argument<java.lang.Integer>("int", 16) {
        @Override
        public Integer parse(String in) {
            Integer value = null;
            try {
                value = Integer.parse(in);
            } catch(final Exception ignored) {}
            return value;
        }
    };

    public static final Argument<Boolean> Boolean = new Argument<java.lang.Boolean>("boolean", true) {
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

    public static final Argument<String> String = new Argument<java.lang.String>("String", "Example") {
        @Override
        public String parse(String in) {
            return in;
        }
    };
}
