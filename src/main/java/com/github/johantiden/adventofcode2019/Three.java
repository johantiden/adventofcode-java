package com.github.johantiden.adventofcode2019;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class Three {


    static Position findClosestIntersection(String wire1, String wire2) {
        List<Position> wire1Steps = walk(wire1);
        List<Position> intersections = walkAndCollectIntersections(wire2, wire1Steps);

        Position closest = findClosestIntersection(intersections);
        return closest;
    }

    private static Position findClosestIntersection(List<Position> intersections) {
        Comparator<Position> comparator = Comparator.comparing(Three::distance);
        return intersections.stream()
                .min(comparator)
                .orElseThrow(() -> new RuntimeException("There was no first!"));
    }




    private static List<Position> walkAndCollectIntersections(String wire2, List<Position> wire1Steps) {
        List<Position> intersections = new ArrayList<>();

        List<Position> wire2Steps = walk(wire2);

        wire2Steps.forEach(p -> {
            if (wire1Steps.contains(p)) {
                intersections.add(p);
            }
        });
        return intersections;
    }


    private static void walkAndPaint(String wire1, SparseBitmap sparseBitmap) {
        walkAnd(wire1, p -> sparseBitmap.put(p, true));

    }

    private static void walkAnd(String wire, Consumer<Position> then) {
        List<Position> steps = walk(wire);
        steps.forEach(then);
    }

    private static List<Position> walk(String wire) {
        List<Position> steps = new ArrayList<>();
        Position position = Position.ZERO;

        String[] words = wire.split(",");
        for (String word : words) {
            char direction = word.charAt(0);
            int length = Integer.parseInt(word.substring(1));
            int dx = getDx(direction);
            int dy = getDy(direction);


            for (int i = 0; i < length; i++) {
                Position newPosition = new Position(position.x + dx, position.y + dy);
                steps.add(newPosition);
                position = newPosition;
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


    public static int distance(Position closest) {
        return Math.abs(closest.x) + Math.abs(closest.y);
    }


    private static class SparseBitmap {
        private final Map<Position, Boolean> bitmap = new HashMap<>();

        public void put(Position p, boolean value) {
            bitmap.put(p, value);
        }

        public boolean get(Position p) {
            return bitmap.getOrDefault(p, false);
        }
    }

    static class Position {
        public static final Position ZERO = new Position(0, 0);
        public final int x;
        public final int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
