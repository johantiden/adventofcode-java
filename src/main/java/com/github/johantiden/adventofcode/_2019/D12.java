package com.github.johantiden.adventofcode._2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class D12 {


    static World parse(String input) {
        input = input.replaceAll("[<>,xyz=]", "");

        String[] rows = input.split("\n");

        List<Moon1D> moonsX = new ArrayList<>();
        List<Moon1D> moonsY = new ArrayList<>();
        List<Moon1D> moonsZ = new ArrayList<>();
        for (String row : rows) {
            String[] dimensions = row.split(" ");
            IntVector3 position = new IntVector3(
                    Integer.parseInt(dimensions[0]),
                    Integer.parseInt(dimensions[1]),
                    Integer.parseInt(dimensions[2]));

            moonsX.add(new Moon1D(position.x));
            moonsY.add(new Moon1D(position.y));
            moonsZ.add(new Moon1D(position.z));
        }

        return new World(moonsX, moonsY, moonsZ);
    }

    static class World {
        final List<Moon1D> moonsX;
        final List<Moon1D> moonsY;
        final List<Moon1D> moonsZ;

        World(List<Moon1D> moonsX, List<Moon1D> moonsY, List<Moon1D> moonsZ) {
            this.moonsX = moonsX;
            this.moonsY = moonsY;
            this.moonsZ = moonsZ;
        }


        void simulateStep() {
            simulateStep1D(moonsX);
            simulateStep1D(moonsY);
            simulateStep1D(moonsZ);
        }

        private void simulateStep1D(List<Moon1D> moons) {
            updateVelocities1D(moons);
            for (Moon1D moon : moons) {
                updatePosition1D(moon);
            }
        }

        private void updatePosition1D(Moon1D moon) {
            moon.position = moon.position + moon.velocity;
        }

        private void updateVelocities1D(List<Moon1D> moons) {
            for (Moon1D a : moons) {
                for (Moon1D b : moons) {
                    a.velocity += signum(b.position - a.position);
                }
            }
        }

        void simulateSteps(int steps) {
            for (int i = 0; i < steps; i++) {
                simulateStep();
            }
        }

        public int getTotalEnergy() {
            int energyX = moonsX.stream().mapToInt(Moon1D::getEnergy).sum();
            int energyY = moonsY.stream().mapToInt(Moon1D::getEnergy).sum();
            int energyZ = moonsZ.stream().mapToInt(Moon1D::getEnergy).sum();

            return energyX + energyY + energyZ;
        }

        // https://www.baeldung.com/java-least-common-multiple
        public static long lcm(long number1, long number2) {
            if (number1 == 0 || number2 == 0) {
                return 0;
            }
            long absNumber1 = Math.abs(number1);
            long absNumber2 = Math.abs(number2);
            long absHigherNumber = Math.max(absNumber1, absNumber2);
            long absLowerNumber = Math.min(absNumber1, absNumber2);
            long lcm = absHigherNumber;
            while (lcm % absLowerNumber != 0) {
                lcm += absHigherNumber;
            }
            return lcm;
        }

        public long findPeriod() {
            long periodX = simulateUntilRepeat1D(moonsX);
            long periodY = simulateUntilRepeat1D(moonsY);
            long periodZ = simulateUntilRepeat1D(moonsZ);


            return lcm(lcm(periodX, periodY), periodZ);
        }


        public long simulateUntilRepeat1D(List<Moon1D> moons) {

            int initial = this.hashCode();

            long countSteps = 1;
            while (true) {
                simulateStep1D(moons);
                if (initial == this.hashCode()) {
                    return countSteps;
                } else {
                    countSteps++;
                }
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
            World world = (World) o;
            return Objects.equals(moonsX, world.moonsX) &&
                    Objects.equals(moonsY, world.moonsY) &&
                    Objects.equals(moonsZ, world.moonsZ);
        }

        @Override
        public int hashCode() {

            return Objects.hash(moonsX, moonsY, moonsZ);
        }
    }

    private static int signum(int value) {
        if (value < 0) {
            return -1;
        } else if (value > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    static class Moon1D {
        int position;
        int velocity;

        public Moon1D(int position) {
            this.position = Objects.requireNonNull(position);
        }

        public Moon1D(int position, int velocity) {
            this.position = position;
            this.velocity = velocity;
        }

        @Override
        public String toString() {
            return "pos=" + position + ", vel=" + velocity;
        }

        public int getEnergy() {
            int pot = Math.abs(position);
            int kin = Math.abs(velocity);
            int energy = pot * kin;
            return energy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Moon1D moon = (Moon1D) o;
            return Objects.equals(position, moon.position) &&
                    Objects.equals(velocity, moon.velocity);
        }

        @Override
        public int hashCode() {

            return Objects.hash(position, velocity);
        }
    }

    static class IntVector3 {
        public static final IntVector3 ZERO = new IntVector3(0, 0, 0);
        final int x;
        final int y;
        final int z;

        private IntVector3(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return "<x="+x+", y="+y+", z="+z+">";
        }

        IntVector3 plus(IntVector3 that) {
            return new IntVector3(
                    x + that.x,
                    y + that.y,
                    z + that.z
            );
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            IntVector3 that = (IntVector3) o;
            return x == that.x &&
                    y == that.y &&
                    z == that.z;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y, z);
        }
    }
}
