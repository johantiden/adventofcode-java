package com.github.johantiden.adventofcode.common.graph;

public record Edge(String a, String b) {
    @Override
    public String toString() {
        return a + "-" + b;
    }
}
