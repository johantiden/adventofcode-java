package com.github.johantiden.adventofcode.common;

public record Pair<A, B>(A a, B b) {
    @Override
    public String toString() {
        return "(" + a + ", " + b + ")";
    }
}
