package com.github.johantiden.adventofcode.common;

public record Pair<L, R>(L left, R right) {
    public static <L, R> Pair<L, R> of(L l, R r) {
        return new Pair<>(l, r);
    }

    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }

    public Pair<L, R> withRight(R right) {
        return new Pair<>(left, right);
    }
}
