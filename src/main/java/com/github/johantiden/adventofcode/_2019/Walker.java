package com.github.johantiden.adventofcode._2019;

class Walker {
    final Position position;
    final Direction direction;

    Walker(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Walker left() {
        return new Walker(position, direction.left());
    }

    public Walker right() {
        return new Walker(position, direction.right());
    }

    public Walker walk(int distance) {
        return new Walker(direction.walk(position, distance), direction);
    }

    public Walker step() {
        return new Walker(direction.step(position), direction);
    }

    public Walker flip() {
        return new Walker(position, direction.flip());
    }
}
