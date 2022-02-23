package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;
import com.github.johantiden.adventofcode.common.Pair;

public class A2021_02 {

    static int a(JList<String> input) {
        Submarine submarineA = parse(input)
                .reduce(Submarine.ZERO, A2021_02::moveA);
        return submarineA.x * submarineA.depth;
    }

    static int b(JList<String> input) {
        Submarine submarineB = parse(input)
                .reduce(Submarine.ZERO, A2021_02::moveB);
        return submarineB.x * submarineB.depth;
    }

    private static JList<Pair<String, Integer>> parse(JList<String> input) {
        return input.map(A2021_02::parse);
    }

    private static Pair<String, Integer> parse(String row) {
        String[] s = row.split(" ");
        return new Pair<>(
                s[0],
                Integer.valueOf(s[1])
        );
    }

    private static Submarine moveB(Submarine sub, Pair<String, Integer> command) {
        Integer amplitude = command.right();

        return switch (command.left()) {
            case "forward" -> new Submarine(sub.x + amplitude, sub.depth + sub.aim*amplitude, sub.aim);
            case "down" -> new Submarine(sub.x, sub.depth, sub.aim + amplitude);
            case "up" -> new Submarine(sub.x, sub.depth, sub.aim - amplitude);
            default -> throw new IllegalArgumentException();
        };
    }

    private static Submarine moveA(Submarine sub, Pair<String, Integer> command) {
        Integer amplitude = command.right();

        return switch (command.left()) {
            case "forward" -> new Submarine(sub.x + amplitude, sub.depth, 0);
            case "down" -> new Submarine(sub.x, sub.depth + amplitude, 0);
            case "up" -> new Submarine(sub.x, sub.depth - amplitude, 0);
            default -> throw new IllegalArgumentException();
        };
    }

    private record Submarine(int x, int depth, int aim) {
        static final Submarine ZERO = new Submarine(0, 0, 0);
    }
}
