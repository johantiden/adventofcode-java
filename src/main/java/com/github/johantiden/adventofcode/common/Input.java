package com.github.johantiden.adventofcode.common;

import javax.annotation.Nonnull;

public class Input {


    @Nonnull
    public static JList<String> splitRows(String input) {
        return split("\n", input);
    }

    @Nonnull
    public static JList<String> split(String regex, String input) {
        return JList.of(input.split(regex));
    }

    public static JList<Integer> splitToInt(String regex, String string) {
        return split(regex, string)
                .map(Integer::parseInt);
    }

    public static JList<String> splitComma(String input) {
        return split(",", input);
    }
}
