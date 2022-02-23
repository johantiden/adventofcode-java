package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.Input;
import com.github.johantiden.adventofcode.common.JList;
import com.github.johantiden.adventofcode.common.graph.Edge;
import com.github.johantiden.adventofcode.common.graph.Graph;

public class A2021_12 {

    static final String EXAMPLE =
"""
""";

    static final String REAL =
"""
""";

    static long a(String input) {
        Graph graph = parse(input);
        WalkState state = new WalkState(graph, JList.of("start"));

        JList<JList<String>> possibleWalks = state.getPossibleWalks();

        return possibleWalks.size();
    }

    static long b(String input) {
        return -1;
    }

    record WalkState(Graph graph, JList<String> pathSoFar) {
        public JList<JList<String>> getPossibleWalks() {
            String currentPosition = pathSoFar.last();
            if (currentPosition.equals("end")) {
                return JList.of(pathSoFar);
            } else {
                JList<String> possibleSteps = graph.getPossibleStepsFrom(currentPosition);
                return possibleSteps
                        .flatMap(step -> {
                            WalkState stateAfterStep = new WalkState(pruneLowercaseWhenLeavingIt(graph, currentPosition), pathSoFar.plus(step));
                            return stateAfterStep.getPossibleWalks();
                        });
            }
        }

        private static Graph pruneLowercaseWhenLeavingIt(Graph graph, String step) {
            if (isLowercase(step)) {
                return graph.withoutNode(step);
            } else {
                return graph;
            }
        }

        private static boolean isLowercase(String step) {
            return step.toLowerCase().equals(step);
        }
    }

    static Graph parse(String input) {
        JList<Edge> edges = Input
                .splitRows(input)
                .map(A2021_12::parseRow);
        return new Graph(edges);
    }

    static Edge parseRow(String input) {
        JList<String> split = JList.ofArray(input.split("-"));
        return new Edge(
                split.get(0),
                split.get(1)
        );
    }


}
