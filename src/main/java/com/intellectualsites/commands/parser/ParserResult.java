package com.intellectualsites.commands.parser;

public class ParserResult<T> {

    private final T result;

    private final boolean parsed;

    private String error;

    public ParserResult(T result, boolean parsed) {
        this.result = result;
        this.parsed = parsed;
    }

    public ParserResult(T result) {
        this.result = result;
        this.parsed = true;
    }

    public ParserResult(String error) {
        this.result = null;
        this.parsed = false;
        this.error = error;
    }

    public T getResult() {
        return this.result;
    }

    public boolean isParsed() {
        return this.parsed;
    }

    public String getError() {
        return this.error;
    }
}
