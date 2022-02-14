package com.github.johantiden.adventofcode._2019;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class D06 {


    public static List<Edge> parseInput(String input) {
        List<Edge> edges = new ArrayList<>();

        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] split = line.split("\\)");
            Edge edge = new Edge(split[1], split[0]);
            edges.add(edge);
        }

        return edges;
    }

    public static int hash(List<Edge> edges) {

        int sum = 0;
        for (Edge edge : edges) {
            int lengthToCOM = getLengthToCOM(edges, edge);
            sum += lengthToCOM;
        }

        return sum;
    }

    private static int getLengthToCOM(List<Edge> edges, Edge edge) {
        if (edge.to.equals("COM")) {
            return 1;
        } else {
            Edge to = findEdgeUsingFrom(edges, edge.to);
            return getLengthToCOM(edges, to) + 1;
        }
    }

    private static Edge findEdgeUsingFrom(List<Edge> edges, String from) {
        return edges.stream()
                .filter(edge -> edge.from.equals(from))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("could not find '"+from+"'"));
    }


    static class Edge {
        private final String from;
        private final String to;

        Edge(String from, String to) {
            this.from = from;
            this.to = to;
        }
    }


    static class PartTwo {

        static int distance(List<Edge> edges, String a, String b) {
            Set<String> visited = new HashSet<>();

            return breadthFirstRecursive(ImmutableList.copyOf(edges), a, b, visited, 0);
        }

        private static int breadthFirstRecursive(ImmutableList<Edge> edges, String current, String target, Set<String> visited, int depth) {

            if (current.equals(target)) {
                return depth;
            }

            List<String> nonVisitedNeighbors = findNeighbors(edges, current).stream()
                    .filter(neighbor -> !visited.contains(neighbor))
                    .collect(Collectors.toList());

            if (nonVisitedNeighbors.isEmpty()) {
                return -1;
            }


            visited.addAll(nonVisitedNeighbors);

            Integer innerDepth = nonVisitedNeighbors.stream()
                    .map(neighbor -> breadthFirstRecursive(edges, neighbor, target, visited, depth + 1))
                    .filter(d -> d >= 0)
                    .min(Integer::compareTo)
                    .orElse(-1);

            return innerDepth;

        }


        private static ImmutableList<String> findNeighbors(List<Edge> edges, String name) {
            List<String> downstream = edges.stream()
                    .filter(edge -> edge.from.equals(name))
                    .map(edge -> edge.to)
                    .collect(Collectors.toList());

            List<String> upstream = edges.stream()
                    .filter(edge -> edge.to.equals(name))
                    .map(edge -> edge.from)
                    .collect(Collectors.toList());


            return ImmutableList.<String>builder()
                    .addAll(downstream)
                    .addAll(upstream)
                    .build();
        }

    }

}
