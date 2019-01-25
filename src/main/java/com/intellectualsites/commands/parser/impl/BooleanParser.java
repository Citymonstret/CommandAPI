package com.intellectualsites.commands.parser.impl;

import com.intellectualsites.commands.parser.Parser;
import com.intellectualsites.commands.parser.ParserResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused") public class BooleanParser extends Parser<Boolean> {

    private static final List<String> trueValues;
    private static final List<String> falseValues;

    static {
        trueValues = Collections.unmodifiableList(Arrays.asList("yes", "true", "on", "1"));
        falseValues = Collections.unmodifiableList(Arrays.asList("no", "false", "off", "0"));
    }

    public BooleanParser() {
        super("boolean", true);
    }

    @Override public ParserResult<Boolean> parse(String in) {
        if (trueValues.contains(in.toLowerCase())) {
            return new ParserResult<>(true, true);
        } else if (falseValues.contains(in.toLowerCase())) {
            return new ParserResult<>(false, true);
        }
        return new ParserResult<>(in + " is not a boolean");
    }
}
