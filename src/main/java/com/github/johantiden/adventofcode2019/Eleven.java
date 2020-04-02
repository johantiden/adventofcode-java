package com.github.johantiden.adventofcode2019;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class Eleven {

    public static void main(String[] args) throws IOException {

        Eleven.Robot robot = createRobot(
                new long[]{3, 8, 1005, 8, 299, 1106, 0, 11, 0, 0, 0, 104, 1, 104, 0, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 108, 1, 8, 10, 4, 10, 102, 1, 8, 28, 1006, 0, 85, 1, 106, 14, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 101, 0, 8, 58, 1, 1109, 15, 10, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1002, 8, 1, 84, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 1, 8, 10, 4, 10, 1002, 8, 1, 105, 1006, 0, 48, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 102, 1, 8, 130, 1006, 0, 46, 1, 1001, 17, 10, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1002, 8, 1, 160, 2, 109, 20, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 1002, 8, 1, 185, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 1, 8, 10, 4, 10, 1001, 8, 0, 207, 1006, 0, 89, 2, 1002, 6, 10, 1, 1007, 0, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 101, 0, 8, 241, 2, 4, 14, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 101, 0, 8, 267, 1, 1107, 8, 10, 1, 109, 16, 10, 2, 1107, 4, 10, 101, 1, 9, 9, 1007, 9, 1003, 10, 1005, 10, 15, 99, 109, 621, 104, 0, 104, 1, 21101, 0, 387239486208l, 1, 21102, 316, 1, 0, 1106, 0, 420, 21101, 0, 936994976664l, 1, 21102, 327, 1, 0, 1105, 1, 420, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 21102, 1, 29192457307l, 1, 21102, 1, 374, 0, 1106, 0, 420, 21101, 0, 3450965211l, 1, 21101, 0, 385, 0, 1106, 0, 420, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 0, 21102, 1, 837901103972l, 1, 21101, 408, 0, 0, 1106, 0, 420, 21102, 867965752164l, 1, 1, 21101, 0, 419, 0, 1105, 1, 420, 99, 109, 2, 22102, 1, -1, 1, 21102, 40, 1, 2, 21102, 451, 1, 3, 21102, 1, 441, 0, 1106, 0, 484, 109, -2, 2106, 0, 0, 0, 1, 0, 0, 1, 109, 2, 3, 10, 204, -1, 1001, 446, 447, 462, 4, 0, 1001, 446, 1, 446, 108, 4, 446, 10, 1006, 10, 478, 1102, 0, 1, 446, 109, -2, 2105, 1, 0, 0, 109, 4, 1201, -1, 0, 483, 1207, -3, 0, 10, 1006, 10, 501, 21101, 0, 0, -3, 22101, 0, -3, 1, 22102, 1, -2, 2, 21101, 1, 0, 3, 21101, 520, 0, 0, 1106, 0, 525, 109, -4, 2106, 0, 0, 109, 5, 1207, -3, 1, 10, 1006, 10, 548, 2207, -4, -2, 10, 1006, 10, 548, 21201, -4, 0, -4, 1105, 1, 616, 22101, 0, -4, 1, 21201, -3, -1, 2, 21202, -2, 2, 3, 21101, 0, 567, 0, 1106, 0, 525, 22101, 0, 1, -4, 21101, 1, 0, -1, 2207, -4, -2, 10, 1006, 10, 586, 21102, 1, 0, -1, 22202, -2, -1, -2, 2107, 0, -3, 10, 1006, 10, 608, 21202, -1, 1, 1, 21102, 608, 1, 0, 106, 0, 483, 21202, -2, -1, -2, 22201, -4, -2, -4, 109, -5, 2105, 1, 0}
        );

        robot.map.put(Position.ZERO, 1);

        robot.run();

        BufferedImage bi = toImage(robot);


        ImageIO.write(bi, "jpg", new File("asdf.jpg"));
        // AGALRGJE
    }

    static Robot createRobot(long[] program) {
        return new Robot(program);
    }

    private static BufferedImage toImage(Robot robot) {
        int left = robot.map.white.stream().map(Position::getX).min(Integer::compareTo).orElse(0);
        int right = robot.map.white.stream().map(Position::getX).max(Integer::compareTo).orElse(0);
        int top = robot.map.white.stream().map(Position::getY).min(Integer::compareTo).orElse(0);
        int bottom = robot.map.white.stream().map(Position::getY).max(Integer::compareTo).orElse(0);

        BufferedImage bi = new BufferedImage(right-left+1, bottom-top+1, BufferedImage.TYPE_3BYTE_BGR);

        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Position position = new Position(x + left, y + top);
                long l = robot.map.get(position);
                int color = l == 1 ? 0xFFFFFF : 0x000000;
                bi.setRGB(x, y, color);

                if (robot.position.equals(position)) {
                    bi.setRGB(x, y, 0x00FF00);
                }
            }
        }

        return bi;
    }

    static class Robot implements IntcodeComputer.Input, IntcodeComputer.Output {
        final Map map = new Map();
        final Map touchedMap = new Map();
        final IntcodeComputer computer;

        Direction direction = Direction.NORTH;
        Position position = Position.ZERO;

        boolean stateNextIsPaint = true;

        private Robot(long[] program) {

            this.computer = IntcodeComputer.of(program, this, this);
        }

        @Override
        public long read() {
            return map.get(position);
        }

        @Override
        public void write(long output) {
            if (stateNextIsPaint) {
                doPaint(output);
            } else {
                doTurn(output);
                doWalk();
            }

            stateNextIsPaint = !stateNextIsPaint;
        }

        private void doWalk() {
            position = direction.step(position);
        }

        private void doPaint(long output) {
            map.put(position, output);
            touchedMap.put(position, 1);
        }

        private void doTurn(long output) {
            if (output == 0) {
                direction = direction.left();
            } else if (output == 1) {
                direction = direction.right();
            } else {
                throw new IllegalArgumentException("Expected 0 or 1 to turn");
            }
        }

        public void run() {
            computer.run();
        }
    }

    static class Map {
        Set<Position> white = new HashSet<>();

        long get(Position position) {
            return white.contains(position) ? 1 : 0;
        }

        void put(Position position, long output) {
            if (output == 1) {
                white.add(position);
            } else if (output == 0) {
                white.remove(position);
            } else {
                throw new IllegalArgumentException("Expected 0 or 1 to paint");
            }
        }
    }

    enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST;

        Direction left() {
            switch (this) {
                case EAST: return NORTH;
                case WEST: return SOUTH;
                case NORTH: return WEST;
                case SOUTH: return EAST;
                default: throw new RuntimeException("Unsupported direction");
            }
        }

        Direction right() {
            switch (this) {
                case EAST: return SOUTH;
                case WEST: return NORTH;
                case NORTH: return EAST;
                case SOUTH: return WEST;
                default: throw new RuntimeException("Unsupported direction");
            }
        }

        public Position step(Position position) {
            switch (this) {
                case EAST: return new Position(position.x+1, position.y);
                case WEST: return new Position(position.x-1, position.y);
                case NORTH: return new Position(position.x, position.y-1);
                case SOUTH: return new Position(position.x, position.y+1);
                default: throw new RuntimeException("Unsupported direction");
            }
        }
    }

}
