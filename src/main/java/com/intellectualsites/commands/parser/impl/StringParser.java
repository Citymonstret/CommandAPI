package com.intellectualsites.commands.parser.impl;

import com.intellectualsites.commands.parser.Parser;
import com.intellectualsites.commands.parser.ParserResult;

public class StringParser extends Parser<String> {

    public StringParser() {
        super("string", "Example...");
    }

    @Override
    public ParserResult<String> parse(String in) {
        return new ParserResult<String>(in, true);
    }

}
