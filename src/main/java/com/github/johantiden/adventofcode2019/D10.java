package com.github.johantiden.adventofcode2019;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Math.PI;

public class D10 {


    static java.util.Map.Entry<Position, Integer> findBestSpotForStation(Map map) {
        HashMap<Position, Integer> countDetectedPerAsteroid = countVisibleAsteroidsFromAllAsteroids(map);

        Comparator<java.util.Map.Entry<Position, Integer>> reversed = Comparator.<java.util.Map.Entry<Position, Integer>, Integer>comparing(java.util.Map.Entry::getValue).reversed();
        java.util.Map.Entry<Position, Integer> best = countDetectedPerAsteroid.entrySet().stream()
                .min(reversed)
                .orElseThrow(() -> new RuntimeException("Could not find first!"));

        return best;
    }

    static HashMap<Position, Integer> countVisibleAsteroidsFromAllAsteroids(Map map) {

        HashMap<Position, Integer> counts = new HashMap<>();


        map.getAllTruthy().stream()
                .forEach(asteroid -> {
                    int visible = countVisibleAsteroidsFrom(asteroid, map);
                    counts.put(asteroid, visible);
                });


        return counts;
    }


    static int countVisibleAsteroidsFrom(Position position, Map map) {

        ImmutableList<Position> visibleAsteroids = getVisibleAsteroidsFrom(position, map);

        int counted = visibleAsteroids.size();
        counted--; // dont count oneself

        return counted;
    }

    static ImmutableList<Position> getVisibleAsteroidsFrom(Position position, Map map) {
        Map and = getVisibleAsteroidsFromAsMap(position, map);
        return and.getAllTruthy();
    }

    static Map getVisibleAsteroidsFromAsMap(Position position, Map map) {
        Map visible = map.getLightMap(position);
        return Map.and(map, visible);
    }


    static Map parse(String str) {
        int width = parseWidth(str);
        int height = parseHeight(str);

        String[] rows = str.split("\n");

        Set<Position> map = new HashSet<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (rows[y].charAt(x) == '#') {
                    map.add(new Position(x, y));
                }
            }
        }

        return new Map(map, width, height);
    }

    private static int parseHeight(String str) {
        String[] rows = str.split("\n");
        return rows.length;
    }

    private static int parseWidth(String str) {
        String[] rows = str.split("\n");
        return rows[0].length();
    }

    public static List<Position> laserTime(Map map, Position laser) {

        List<Position> allDestroyed = new ArrayList<>();

        while (!map.isEmpty()) {
            ImmutableList<Position> visibleAsteroidsFrom = getVisibleAsteroidsFrom(laser, map);
            List<Position> destroyed = laserSpinOnce(laser, visibleAsteroidsFrom);
            destroyed.forEach(d -> map.set(d, false));
            allDestroyed.addAll(destroyed);
        }

        return allDestroyed;
    }

    private static List<Position> laserSpinOnce(Position laser, ImmutableList<Position> visibleAsteroidsFrom) {
        return visibleAsteroidsFrom.stream()
                    .sorted(Comparator.comparing(asteroid -> getLaserAngle(laser, asteroid)))
                    .collect(Collectors.toList());
    }

    static double getLaserAngle(Position laser, Position target) {
        double angle = Position.getAngle(laser, target);
        angle += PI/2;
        angle = Position.properMod(angle, 2*PI);

        return angle;
    }

    static class Map {
        final Set<Position> truthy;
        final int width;
        final int height;

        Map(Set<Position> truthy, int width, int height) {
            this.truthy = truthy;
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            String s = "";

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    s += get(x, y) ? "#" : ".";
                }
                s += "\n";
            }

            return s;
        }

        boolean get(int x, int y) {
            Position position = new Position(x, y);
            return get(position);
        }

        boolean get(Position position) {
            return truthy.contains(position);
        }

        public void set(int x, int y, boolean value) {
            Position position = new Position(x, y);
            set(position, value);
        }

        public void set(Position position, boolean value) {
            if (value) {
                truthy.add(position);
            } else {
                truthy.remove(position);
            }
        }

        Map getLightMap(Position light) {
            List<Position> asteroids = getAllTruthy();
            Map map = fullTrue(width, height);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Position position = new Position(x, y);
                    if (isAnyAsteroidBetween(light, position, asteroids)) {
                        map.set(position, false);
                    }
                }
            }
            return map;
        }

        public static Map and(Map a, Map b) {
            if (a.width != b.width || a.height != b.height) {
                throw new IllegalArgumentException("Dimension must be the same!");
            }

            Set<Position> map = new HashSet<>();

            for (int y = 0; y < a.height; y++) {
                for (int x = 0; x < a.width; x++) {
                    Position position = new Position(x, y);
                    if (a.get(position) && b.get(position)) {
                        map.add(position);
                    }
                }
            }

            return new Map(map, a.width, a.height);
        }

        private static boolean isAnyAsteroidBetween(Position light, Position position, List<Position> asteroids) {
            if (light.equals(position)) {
                return false;
            }

            double lp = distance(light, position);

            return asteroids.stream()
                    .anyMatch(asteroid -> {
                        if (asteroid.equals(light) || asteroid.equals(position)) {
                            return false;
                        }

                        double la = distance(light, asteroid);
                        double pa = distance(position, asteroid);

                        double lapa = la + pa;

                        boolean closeEnough = isCloseEnough(lapa, lp);
                        return closeEnough;
                    });
        }

        private static boolean isCloseEnough(double a, double b) {
            return Math.abs(a-b) < Math.pow(10, -10);
        }

        private static double distance(Position light, Position position) {
            return Math.sqrt((light.x-position.x)*(light.x-position.x) +
                    (light.y-position.y)*(light.y-position.y));
        }

        private static Map fullTrue(int width, int height) {
            Set<Position> positions = getAllPossiblePositions(width, height);

            return new Map(positions, width, height);
        }

        private static Set<Position> getAllPossiblePositions(int width, int height) {
            Set<Position> positions = new HashSet<>();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    positions.add(new Position(x, y));
                }
            }
            return positions;
        }

        private ImmutableList<Position> getAllTruthy() {
            return ImmutableList.copyOf(truthy);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Map map = (Map) o;
            return width == map.width &&
                    height == map.height &&
                    Objects.equals(truthy, map.truthy);
        }

        @Override
        public int hashCode() {

            return Objects.hash(truthy, width, height);
        }

        public boolean isEmpty() {
            return truthy.isEmpty();
        }
    }

}
