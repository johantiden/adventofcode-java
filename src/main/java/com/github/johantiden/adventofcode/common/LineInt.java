package com.github.johantiden.adventofcode.common;

public record LineInt(PointInt start, PointInt end) {
    public RectangleInt getAreaOfInterest() {
        int left = Math.min(start.x(), end.x());
        int right = Math.max(start.x(), end.x());
        int top = Math.min(start.y(), end.y());
        int bottom = Math.max(start.y(), end.y());

        return RectangleInt.leftRightTopBottom(left, right, top, bottom);

    }
}
