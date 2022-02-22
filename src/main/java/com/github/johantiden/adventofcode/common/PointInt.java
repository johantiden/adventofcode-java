package com.github.johantiden.adventofcode.common;

public record PointInt(int x, int y) {


    public JList<PointInt> neighbors4() {
        return JList.of(
                plus(0, 1),
                plus(1, 0),
                plus(0, -1),
                plus(-1, 0)
        );
    }

    public JList<PointInt> neighbors9() {
        return Lists.rangeClosed(-1, 1)
                .flatMap(yy ->
                        Lists.rangeClosed(-1, 1).map(xx -> plus(xx, yy)));
    }

    public PointInt plus(int x, int y) {
        return new PointInt(this.x+x, this.y+y);
    }
}
