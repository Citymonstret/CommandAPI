package com.intellectualsites.commands.parser;

public class Parserable<T> {

    private final String name;

    private final Parser<T> parser;

    private final String desc;

    public Parserable(final String name, final Parser<T> parser, final String desc) {
        this.name = name;
        this.parser = parser;
        this.desc = desc;
    }

    public ParserResult<T> parse(String in) {
        return parser.parse(in);
    }

    public String getName() {
        return this.name;
    }

    public Parser<T> getParser() {
        return this.parser;
    }

    public String getDesc() {
        return this.desc;
    }
}
