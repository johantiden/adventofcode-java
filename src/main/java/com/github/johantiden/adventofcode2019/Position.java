package com.github.johantiden.adventofcode2019;

import java.util.Objects;

import static java.lang.Math.PI;

class Position {
    public static final Position ZERO = new Position(0, 0);
    public final int x;
    public final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double getAngle(Position a, Position b) {
        return properMod(Math.atan2(b.y-a.y, b.x-a.x), PI*2);
    }

    static double properMod(double value, double divisor) {
        if (value >= 0) {
            return value % divisor;
        } else {
            while (value < 0) {
                value += divisor;
            }
            return value;
        }
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
