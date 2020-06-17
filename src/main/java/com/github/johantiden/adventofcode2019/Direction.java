package com.github.johantiden.adventofcode2019;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

enum Direction {
    NORTH(1),
    SOUTH(2),
    WEST(3),
    EAST(4),
    ;
    public static final ArrayList<Direction> VALUES_SORTED = Lists.newArrayList(
            WEST, NORTH, EAST, SOUTH
    );

    private final long value;

    Direction(long value) {
        this.value = value;
    }

    public static Direction of(int i) {
        switch (i) {
            case 1: return NORTH;
            case 2: return SOUTH;
            case 3: return WEST;
            case 4: return EAST;
            default:throw new IllegalArgumentException("IllegalArgumentException");
        }
    }

    public static List<Direction> theValues() {
        return VALUES_SORTED;
    }

    public Position step(Position position) {
        switch (this) {
            case NORTH: return position.plus(0, -1);
            case SOUTH: return position.plus(0, 1);
            case WEST: return position.plus(-1, 0);
            case EAST: return position.plus(1, 0);
        }
        throw new IllegalStateException();
    }

    public long value() {
        return value;
    }

    public Direction right() {
        switch (this) {
            case EAST: return SOUTH;
            case WEST: return NORTH;
            case NORTH: return EAST;
            case SOUTH: return WEST;
        }
        throw new IllegalStateException();
    }

    public Direction left() {
        return right().right().right();
    }

    public Direction flip() {
        return right().right();
    }

    public Position walk(Position from, int distance) {
        if (distance == 0) {
            return from;
        }

        return walk(step(from), distance-1);
    }
}
