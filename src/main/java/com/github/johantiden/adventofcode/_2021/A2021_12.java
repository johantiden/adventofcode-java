package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.Input;
import com.github.johantiden.adventofcode.common.JList;
import com.github.johantiden.adventofcode.common.graph.Edge;
import com.github.johantiden.adventofcode.common.graph.Graph;

import java.util.Comparator;
import java.util.function.BiFunction;

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

        JList<JList<String>> possibleWalks = state.getPossibleWalks(WalkState::pruneLowercaseWhenLeavingIt)
                .distinct();

        return possibleWalks.size();
    }

    static long b(String input) {
        Graph graph = parse(input);
        WalkState state = new WalkState(graph, JList.of("start"));

        JList<JList<String>> possibleWalks = state.getPossibleWalks(WalkState::pruneLowercaseWhenLeavingItAndHasBeenThereTwice)
                .distinct();

        return possibleWalks.size();
    }

    static String toString(JList<JList<String>> possibleWalks) {
        return possibleWalks
                .map(l -> l.toString().replaceAll(" ", ""))
                .sorted(Comparator.comparing(s -> s.replaceAll(",", "")))
                .toString();
    }

    record WalkState(Graph graph, JList<String> pathSoFar) {
        public JList<JList<String>> getPossibleWalks(BiFunction<WalkState, String, Graph> pruner) {
            String currentPosition = pathSoFar.last();
            if (currentPosition.equals("end")) {
                return JList.of(pathSoFar);
            } else {
                JList<String> possibleSteps = graph.getPossibleStepsFrom(currentPosition)
                        .sorted(Comparator.naturalOrder()); // Sorting not necessary but produces the same output as the examples.
                return possibleSteps
                        .flatMap(step -> {
                            WalkState stateAfterStep = new WalkState(pruner.apply(this, currentPosition), pathSoFar.plus(step));
                            return stateAfterStep.getPossibleWalks(pruner);
                        });
            }
        }

        private static Graph pruneLowercaseWhenLeavingIt(WalkState walkState, String leaving) {
            if (isLowercase(leaving)) {
                return walkState.graph.withoutNode(leaving);
            } else {
                return walkState.graph;
            }
        }

        private static Graph pruneLowercaseWhenLeavingItAndHasBeenThereTwice(WalkState walkState, String leaving) {
            boolean hasBeenThereTwice = walkState.pathSoFar.filter(node -> node.equals(leaving)).size() == 2;
            boolean allowedToGoBackAgain = !(isLowercase(leaving) && hasBeenThereTwice) && !leaving.equals("start") && !leaving.equals("end");
            if (!allowedToGoBackAgain) {
                return walkState.graph.withoutNode(leaving);
            } else {
                return walkState.graph;
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
