package com.github.johantiden.adventofcode2019;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class D03 {


    static Position findClosestIntersection(String wire1, String wire2) {
        List<Step> wire1Steps = walk(wire1);
        List<Step> wire2Steps = walk(wire2);
        List<Pair<Step, Step>> intersections = findIntersections(wire1Steps, wire2Steps);

        Comparator<Pair<Step, Step>> comparator = Comparator.comparing(D03::manhattanDistance);
        Pair<Step, Step> closest = findFirst(intersections, comparator);
        return closest.a.position;
    }

    static Pair<Step, Step> findShortestIntersection(String wire1, String wire2) {
        List<Step> wire1Steps = walk(wire1);
        List<Step> wire2Steps = walk(wire2);
        List<Pair<Step, Step>> intersections = findIntersections(wire1Steps, wire2Steps);

        Comparator<Pair<Step, Step>> comparator = Comparator.comparing(D03::asdf);
        Pair<Step, Step> closest = findFirst(intersections, comparator);
        return closest;
    }

    private static Pair<Step, Step> findFirst(List<Pair<Step, Step>> intersections, Comparator<Pair<Step, Step>> comparator) {
        return intersections.stream()
                .min(comparator)
                .orElseThrow(() -> new RuntimeException("There was no first!"));
    }


    private static List<Pair<Step, Step>> findIntersections(List<Step> wire1Steps,  List<Step> wire2Steps) {
        List<Pair<Step, Step>> intersections = new ArrayList<>();

        for (Step wire1Step : wire1Steps) {
            for (Step wire2Step : wire2Steps) {
                if (wire1Step.position.equals(wire2Step.position)) {
                    intersections.add(new Pair<>(wire1Step, wire2Step));
                }
            }
        }


        return intersections;
    }

    private static List<Step> walk(String wire) {
        List<Step> steps = new ArrayList<>();
        Step step = Step.ZERO;

        String[] words = wire.split(",");
        for (String word : words) {
            char direction = word.charAt(0);
            int length = Integer.parseInt(word.substring(1));
            int dx = getDx(direction);
            int dy = getDy(direction);


            for (int i = 0; i < length; i++) {
                Position newPosition = new Position(step.position.x + dx, step.position.y + dy);
                Step newStep = new Step(step.stepCount + 1, newPosition);
                steps.add(newStep);
                step = newStep;
            }
        }

        return steps;
    }

    private static int getDx(char direction) {
        switch (direction) {
            case 'L': return -1;
            case 'R': return 1;
            default: return 0;
        }
    }

    private static int getDy(char direction) {
        switch (direction) {
            case 'U': return -1;
            case 'D': return 1;
            default: return 0;
        }
    }


    public static int manhattanDistance(Pair<Step, Step> intersection) {
        assert intersection.a.position.equals(intersection.b.position);
        return Math.abs(intersection.a.position.x) + Math.abs(intersection.a.position.y);
    }

    public static int manhattanDistance(Position intersection) {
        return Math.abs(intersection.x) + Math.abs(intersection.y);
    }

    public static int asdf(Pair<Step, Step> intersection) {
        return intersection.a.stepCount + intersection.b.stepCount;
    }

    static class Pair<A, B> {
        final A a;
        final B b;

        Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }

    static class Step {
        public static final Step ZERO = new Step(0, Position.ZERO);
        final int stepCount;
        final Position position;

        Step(int stepCount, Position position) {
            this.stepCount = stepCount;
            this.position = position;
        }
    }

}
