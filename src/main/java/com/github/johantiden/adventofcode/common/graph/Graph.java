package com.github.johantiden.adventofcode.common.graph;

import com.github.johantiden.adventofcode.common.JList;

public record Graph(JList<Edge> edges) {
    public JList<String> getPossibleStepsFrom(String position) {
        JList<String> goingRight = edges
                .filter(e -> e.a().equals(position))
                .map(Edge::b);
        JList<String> goingLeft = edges.filter(e -> e.b().equals(position))
                .map(Edge::a);

        return goingRight
                .concat(goingLeft)
                .distinct();
    }

    public Graph withoutNode(String step) {
        return new Graph(
                edges
                        .filter(e -> !e.a().equals(step))
                        .filter(e -> !e.b().equals(step))
        );
    }
}
