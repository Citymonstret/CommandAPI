package com.intellectualsites.commands.parser;

public abstract class Parser<T> {

    private String name;

    private final Object example;

    public Parser(String name, Object example) {
        this.name = name;
        this.example = example;
    }

    public abstract ParserResult<T> parse(String in);

    @Override
    public final String toString() {
        return this.getName();
    }

    public String getName() {
        return this.name;
    }

    public Object getExample() {
        return this.example;
    }

}
