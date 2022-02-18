package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;
import com.github.johantiden.adventofcode.common.Pair;

public class A2021_02 {

    static final JList<String> EXAMPLE = JList.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
    static final JList<String> REAL = JList.of("forward 9", "down 9", "up 4", "down 5", "down 6", "up 6", "down 7", "down 1", "forward 6", "down 4", "forward 8", "up 5", "forward 9", "down 1", "down 4", "up 4", "up 5", "up 4", "down 1", "forward 8", "down 1", "forward 2", "forward 8", "down 9", "forward 2", "down 6", "down 2", "up 8", "down 6", "forward 9", "forward 7", "down 6", "forward 3", "down 2", "forward 4", "down 5", "up 2", "down 9", "down 8", "up 5", "forward 5", "forward 4", "up 9", "forward 9", "down 8", "forward 8", "forward 2", "up 8", "down 7", "forward 8", "down 3", "forward 6", "up 9", "forward 9", "forward 4", "forward 9", "forward 6", "down 4", "up 2", "forward 4", "up 5", "up 6", "forward 9", "down 3", "forward 4", "forward 9", "down 1", "forward 1", "up 6", "up 4", "forward 7", "up 7", "up 3", "forward 2", "forward 8", "forward 6", "down 4", "forward 2", "forward 3", "down 7", "down 5", "down 8", "down 5", "forward 1", "down 8", "down 2", "down 8", "down 3", "forward 4", "forward 8", "forward 9", "down 1", "forward 8", "down 1", "down 6", "down 7", "down 7", "forward 5", "forward 3", "down 2", "down 1", "forward 2", "forward 1", "down 6", "down 4", "up 5", "up 9", "down 4", "forward 9", "down 2", "down 5", "down 4", "down 2", "forward 2", "forward 4", "forward 6", "forward 6", "forward 3", "down 6", "up 5", "forward 8", "forward 3", "down 9", "down 3", "forward 4", "forward 2", "down 9", "down 8", "down 7", "down 3", "forward 2", "down 7", "down 3", "down 5", "forward 6", "up 9", "up 8", "forward 5", "down 6", "down 1", "down 6", "down 5", "forward 7", "down 2", "forward 8", "forward 7", "forward 2", "forward 8", "up 6", "forward 5", "down 2", "down 5", "up 8", "up 6", "forward 1", "down 4", "up 5", "up 5", "up 5", "forward 4", "up 1", "forward 3", "down 9", "down 6", "up 1", "forward 1", "forward 2", "forward 1", "forward 4", "forward 6", "forward 6", "up 7", "down 7", "down 7", "down 9", "forward 9", "down 1", "down 5", "down 1", "down 7", "down 1", "up 6", "forward 2", "down 4", "up 3", "up 2", "forward 6", "up 4", "down 1", "down 5", "forward 9", "up 4", "up 3", "forward 3", "up 7", "forward 2", "forward 5", "down 9", "forward 7", "forward 4", "down 1", "up 2", "forward 4", "up 4", "down 2", "forward 4", "up 5", "up 1", "down 9", "down 3", "up 6", "forward 7", "up 7", "forward 2", "down 4", "up 3", "up 3", "forward 4", "up 5", "down 3", "up 8", "forward 6", "forward 8", "down 1", "down 9", "down 7", "forward 7", "forward 5", "forward 2", "up 9", "forward 3", "forward 1", "down 7", "down 6", "forward 5", "up 3", "forward 6", "down 4", "forward 9", "down 7", "forward 9", "down 9", "down 5", "down 6", "down 2", "down 2", "down 8", "down 3", "down 9", "forward 5", "up 6", "forward 1", "down 3", "down 2", "up 1", "up 6", "forward 3", "down 6", "down 6", "up 9", "up 8", "forward 2", "down 7", "forward 5", "up 9", "down 7", "down 3", "forward 2", "forward 2", "up 9", "forward 1", "forward 7", "down 9", "forward 6", "forward 7", "up 8", "down 7", "down 5", "down 3", "up 6", "down 5", "forward 6", "down 9", "down 6", "up 9", "down 7", "forward 2", "down 5", "up 4", "down 4", "down 8", "forward 7", "down 9", "forward 8", "forward 6", "down 7", "down 1", "forward 5", "up 6", "forward 4", "up 7", "up 4", "up 5", "forward 9", "forward 5", "forward 4", "down 6", "down 5", "forward 2", "forward 7", "down 8", "forward 3", "up 5", "down 2", "up 3", "forward 4", "up 5", "up 2", "forward 4", "forward 1", "forward 1", "forward 4", "forward 4", "down 2", "forward 1", "forward 1", "up 5", "up 7", "down 8", "down 4", "forward 2", "forward 2", "down 3", "forward 7", "down 8", "up 3", "forward 2", "down 2", "forward 3", "up 2", "forward 3", "up 6", "down 7", "up 7", "down 3", "up 9", "forward 3", "forward 7", "down 7", "up 9", "down 6", "down 2", "forward 8", "forward 8", "up 7", "down 6", "forward 2", "forward 1", "down 4", "up 2", "forward 6", "up 7", "down 5", "up 1", "forward 3", "forward 9", "up 4", "forward 5", "forward 8", "down 3", "up 5", "forward 9", "down 6", "up 9", "forward 5", "down 4", "down 1", "down 6", "up 9", "up 2", "forward 5", "down 1", "up 3", "down 5", "forward 2", "down 4", "forward 5", "down 6", "down 4", "down 4", "forward 1", "down 7", "down 2", "forward 4", "forward 5", "up 9", "down 6", "down 2", "forward 7", "up 8", "down 9", "forward 7", "down 5", "down 2", "down 8", "down 8", "up 4", "up 3", "down 3", "down 7", "forward 4", "forward 6", "down 4", "up 7", "forward 4", "forward 4", "forward 1", "down 3", "down 2", "forward 7", "forward 2", "up 9", "down 7", "up 7", "forward 2", "forward 6", "forward 9", "down 3", "forward 7", "forward 5", "up 5", "up 1", "forward 6", "forward 4", "down 2", "forward 3", "forward 9", "down 1", "forward 6", "forward 7", "forward 1", "up 7", "up 4", "forward 7", "forward 8", "down 7", "down 8", "down 9", "forward 7", "down 9", "up 6", "down 7", "up 3", "down 7", "forward 4", "forward 9", "forward 1", "down 4", "forward 1", "up 4", "up 4", "forward 9", "forward 8", "up 4", "down 2", "forward 4", "forward 2", "forward 8", "down 2", "up 6", "down 4", "forward 6", "forward 5", "down 2", "forward 9", "down 5", "forward 5", "down 3", "down 2", "up 9", "down 3", "forward 6", "forward 6", "up 9", "down 1", "forward 4", "up 3", "forward 1", "forward 3", "forward 3", "down 6", "down 2", "forward 8", "down 4", "forward 8", "forward 8", "forward 5", "up 6", "forward 3", "down 1", "down 8", "forward 3", "forward 4", "down 2", "down 7", "up 8", "forward 3", "forward 8", "up 2", "forward 6", "down 4", "forward 9", "forward 5", "down 1", "forward 6", "forward 2", "down 3", "up 4", "down 7", "down 2", "up 2", "forward 7", "down 6", "down 2", "up 5", "up 5", "down 9", "down 7", "down 3", "down 1", "down 9", "forward 4", "down 4", "forward 7", "forward 8", "forward 4", "up 6", "forward 6", "forward 9", "down 2", "forward 4", "down 8", "down 4", "forward 5", "forward 2", "up 4", "down 3", "up 8", "up 1", "down 1", "forward 9", "up 3", "up 1", "forward 1", "forward 7", "forward 1", "down 7", "forward 7", "forward 7", "down 7", "forward 4", "up 6", "forward 3", "down 1", "up 1", "up 8", "forward 5", "forward 2", "up 4", "forward 7", "down 2", "down 3", "down 8", "up 7", "up 5", "forward 8", "down 5", "down 3", "down 9", "forward 6", "forward 4", "down 9", "up 5", "forward 3", "up 7", "up 9", "up 1", "forward 1", "forward 3", "forward 1", "up 8", "up 4", "down 1", "down 8", "down 3", "down 1", "down 1", "down 9", "forward 4", "down 3", "forward 9", "forward 2", "down 1", "forward 9", "up 7", "forward 6", "up 4", "forward 8", "forward 3", "down 2", "down 2", "down 2", "up 5", "forward 1", "up 1", "forward 7", "down 1", "forward 1", "down 8", "up 4", "up 1", "forward 7", "down 8", "down 9", "forward 2", "forward 1", "up 3", "forward 4", "up 8", "forward 5", "down 2", "forward 6", "forward 8", "up 9", "forward 2", "down 7", "down 4", "up 3", "forward 1", "forward 6", "forward 9", "down 1", "down 8", "down 1", "down 2", "forward 3", "forward 9", "forward 2", "forward 4", "forward 7", "forward 3", "up 8", "up 9", "forward 3", "forward 6", "down 5", "up 6", "down 8", "forward 5", "up 4", "up 9", "forward 6", "forward 3", "up 9", "forward 8", "forward 5", "forward 9", "forward 7", "up 6", "forward 3", "forward 1", "up 4", "forward 9", "forward 8", "up 1", "up 2", "down 3", "down 4", "down 9", "down 4", "down 5", "down 6", "down 2", "down 5", "forward 6", "forward 4", "up 2", "up 7", "down 5", "down 9", "forward 3", "down 5", "forward 6", "down 7", "forward 1", "forward 7", "forward 9", "forward 7", "forward 4", "forward 4", "up 1", "up 4", "down 6", "up 2", "up 1", "down 4", "forward 2", "down 4", "forward 6", "down 3", "up 6", "down 2", "up 3", "forward 1", "forward 9", "forward 3", "up 9", "forward 7", "forward 5", "forward 4", "down 5", "down 9", "forward 6", "forward 7", "up 1", "forward 7", "forward 2", "forward 2", "forward 5", "forward 6", "down 3", "down 7", "down 3", "down 4", "down 6", "down 1", "forward 2", "down 8", "forward 4", "forward 7", "up 1", "down 4", "down 1", "down 2", "down 3", "up 3", "forward 9", "forward 2", "down 8", "up 3", "forward 8", "forward 7", "up 8", "down 8", "forward 2", "down 9", "down 9", "down 5", "forward 1", "forward 3", "forward 6", "up 1", "up 2", "forward 1", "down 3", "up 6", "forward 2", "forward 8", "forward 2", "down 3", "forward 8", "forward 9", "down 7", "down 3", "down 2", "down 9", "down 3", "up 6", "forward 9", "forward 5", "forward 1", "forward 9", "down 9", "up 2", "down 1", "up 6", "forward 6", "down 3", "forward 6", "forward 3", "forward 5", "forward 4", "up 2", "up 4", "up 6", "forward 1", "forward 6", "up 6", "up 4", "up 7", "down 8", "down 5", "up 1", "up 1", "down 5", "forward 5", "down 9", "forward 8", "down 3", "up 4", "down 9", "down 1", "forward 2", "forward 9", "down 3", "down 8", "down 5", "down 6", "forward 7", "forward 1", "down 9", "down 7", "forward 8", "forward 2", "up 1", "up 1", "forward 7", "up 1", "forward 2", "down 9", "up 4", "forward 5", "down 1", "up 1", "down 8", "down 3", "up 1", "down 8", "down 7", "down 2", "forward 9", "down 5", "forward 2", "up 2", "up 6", "up 4", "forward 6", "up 5", "forward 5", "forward 4", "forward 8", "down 8", "down 6", "down 1", "down 3", "down 6", "forward 8", "up 1", "up 5", "down 4", "forward 4", "down 9", "forward 4", "up 6", "down 7", "forward 4", "down 3", "down 4", "forward 1", "forward 3", "down 1", "down 7", "up 8", "down 3", "down 4", "down 3", "forward 3", "down 8", "forward 8", "down 3", "down 7", "forward 2", "up 2", "forward 7", "down 9", "up 7", "forward 5", "down 2", "down 5", "up 4", "up 8", "forward 8", "forward 9", "forward 8", "down 8", "forward 6", "forward 9", "forward 6", "forward 8", "forward 6", "forward 8", "forward 2", "down 7", "down 3", "forward 7", "down 4", "down 5", "up 1", "forward 5", "down 3", "down 7", "up 4", "forward 9", "down 2", "down 3", "forward 1", "up 6", "down 1", "down 9", "forward 8", "forward 9", "forward 2", "down 6", "down 4", "up 3", "up 8", "forward 1", "down 3", "up 8", "up 7", "down 4", "up 3", "down 7", "down 2", "down 5", "down 7", "down 2", "forward 2", "down 3", "up 2", "forward 8", "up 1", "forward 2", "up 4", "forward 1", "forward 8", "forward 6", "forward 2", "down 2", "forward 5", "up 4", "down 9", "down 7", "forward 2", "down 9", "down 9", "forward 6", "down 8", "down 4", "down 7", "down 9", "forward 7", "forward 7", "up 6", "forward 3", "forward 5", "forward 6", "down 8", "up 1", "forward 2", "up 4", "up 2", "down 8", "down 9", "down 1", "down 3", "forward 7", "forward 5", "forward 6", "up 6", "down 7", "up 8", "up 1", "forward 8", "down 5", "up 1", "down 2", "down 5", "forward 6", "down 4", "forward 5", "down 4", "forward 3", "down 5", "up 4", "up 7", "forward 2", "up 2", "down 8", "forward 6");

    static int a(JList<String> input) {
        Submarine submarineA = parse(input)
                .reduce(Submarine.ZERO, A::move);
        return submarineA.x * submarineA.depth;
    }

    static int b(JList<String> input) {
        Submarine submarineB = parse(input)
                .reduce(Submarine.ZERO, B::move);
        return submarineB.x * submarineB.depth;
    }

    private static JList<Pair<Command, Integer>> parse(JList<String> input) {
        return input.map(A2021_02::parse);
    }

    private static Pair<Command, Integer> parse(String row) {
        String[] s = row.split(" ");
        return new Pair<>(
                Command.valueOf(s[0].toUpperCase()),
                Integer.valueOf(s[1])
        );
    }

    private record Submarine(int x, int depth, int aim) {
        static final Submarine ZERO = new Submarine(0, 0, 0);
    }

    private enum Command {
        FORWARD,
        DOWN,
        UP
    }

    private static class A {

        private static Submarine move(Submarine sub, Pair<Command, Integer> command) {
            Integer amplitude = command.right();

            return switch (command.left()) {
                case FORWARD -> new Submarine(sub.x + amplitude, sub.depth, 0);
                case DOWN -> new Submarine(sub.x, sub.depth + amplitude, 0);
                case UP -> new Submarine(sub.x, sub.depth - amplitude, 0);
            };
        }
    }

    private static class B {
        private static Submarine move(Submarine sub, Pair<Command, Integer> command) {
            Integer amplitude = command.right();

            return switch (command.left()) {
                case FORWARD -> new Submarine(sub.x + amplitude, sub.depth + sub.aim*amplitude, sub.aim);
                case DOWN -> new Submarine(sub.x, sub.depth, sub.aim + amplitude);
                case UP -> new Submarine(sub.x, sub.depth, sub.aim - amplitude);
            };
        }
    }
}
