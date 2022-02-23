package com.github.johantiden.adventofcode.common;

import javax.annotation.Nonnull;

public class Input {


    @Nonnull
    public static JList<String> splitRows(String input) {
        return JList.ofArray(input.split("\n"));
    }
}
