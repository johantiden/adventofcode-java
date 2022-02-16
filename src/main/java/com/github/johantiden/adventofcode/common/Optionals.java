package com.github.johantiden.adventofcode.common;

import java.util.Optional;
import java.util.function.BinaryOperator;

public class Optionals {
    public static <T> Optional<T> reduceOptional(Optional<T> a, Optional<T> b, BinaryOperator<T> reducer) {
        if (a.isPresent() && b.isPresent()) {
            return Optional.of(reducer.apply(a.get(), b.get()));
        } else if (a.isPresent()) {
            return a;
        } else {
            return b;
        }
    }
}
