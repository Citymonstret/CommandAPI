package com.intellectualsites.commands.argument;

public class Argument<T> {

    private final String name;
    private final String desc;
    private final ArgumentType<T> argumentType;

    public Argument(final String name, final ArgumentType<T> argumentType, final String desc) {
        this.name = name;
        this.desc = desc;
        this.argumentType = argumentType;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getDesc() {
        return this.desc;
    }

    public T parse(String in) {
        return argumentType.parse(in);
    }

    public ArgumentType<T> getArgumentType() {
        return this.argumentType;
    }
}
