package com.github.johantiden.adventofcode.common;

public record Pair<A, B>(A a, B b) {
    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ")";
    }
}
