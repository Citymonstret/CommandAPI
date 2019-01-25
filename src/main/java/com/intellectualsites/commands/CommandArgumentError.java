package com.intellectualsites.commands;

import com.intellectualsites.commands.parser.ParserResult;
import com.intellectualsites.commands.parser.Parserable;

@SuppressWarnings("unused") public class CommandArgumentError {

    private final ParserResult result;
    private final Parserable parserable;

    CommandArgumentError(final ParserResult parserResult, final Parserable parserable) {
        this.result = parserResult;
        this.parserable = parserable;
    }

    public ParserResult getResult() {
        return this.result;
    }

    public Parserable getParserable() {
        return this.parserable;
    }

}
