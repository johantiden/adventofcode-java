package com.github.johantiden.adventofcode.common;

public record PointInt(int x, int y) {
    public PointInt plus(int x, int y) {
        return new PointInt(this.x+x, this.y+y);
    }
}
