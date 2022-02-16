package com.github.johantiden.adventofcode.common;

public record RectangleInt(int left, int top, int width, int height) {
    public static final RectangleInt EMPTY = new RectangleInt(0, 0, 0, 0);

    public static RectangleInt leftRightTopBottom(int left, int right, int top, int bottom) {
        return new RectangleInt(left, top, right - left, bottom - top);
    }

    public RectangleInt union(RectangleInt that) {
        return leftRightTopBottom(
                Math.min(left, that.left),
                Math.max(right(), that.right()),
                Math.min(top, that.top),
                Math.max(bottom(), that.bottom()));
    }

    public int bottom() {
        return top + height;
    }

    public int right() {
        return left + width;
    }

    public boolean contains(int x, int y) {
        return x >= left &&
                x <= right() &&
                y >= top &&
                y <= bottom();
    }
}
