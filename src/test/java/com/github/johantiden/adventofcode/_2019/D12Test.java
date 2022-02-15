package com.github.johantiden.adventofcode._2019;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D12Test {


    @Test
    public void testParse() {

        A2019_12.World world = A2019_12.parse(
"<x=-1, y=0, z=2>\n" +
"<x=2, y=-10, z=-7>\n" +
"<x=4, y=-8, z=8>\n" +
"<x=3, y=5, z=-1>"
        );


        assertEquals(-1, world.moonsX.get(0).position);
        assertEquals(0, world.moonsY.get(0).position);
        assertEquals(2, world.moonsZ.get(0).position);

    }

    @Test
    public void testSimulate() {

        A2019_12.World world = A2019_12.parse(
"<x=-1, y=0, z=2>\n" +
"<x=2, y=-10, z=-7>\n" +
"<x=4, y=-8, z=8>\n" +
"<x=3, y=5, z=-1>"
        );


        System.out.println("After 0 steps:");
        System.out.println(world);

        assertEquals(-1, world.moonsX.get(0).position);
        assertEquals(0, world.moonsY.get(0).position);
        assertEquals(2, world.moonsZ.get(0).position);

        world.simulateStep();


        System.out.println("After 1 steps:");
        System.out.println(world);
        assertEquals(2, world.moonsX.get(0).position);
        assertEquals(-1, world.moonsY.get(0).position);
        assertEquals(1, world.moonsZ.get(0).position);
        assertEquals(1, world.moonsX.get(1).velocity);

        world.simulateStep();

        System.out.println("After 2 steps:");
        System.out.println(world);
        assertEquals(5, world.moonsX.get(0).position);
        assertEquals(-3, world.moonsY.get(0).position);
        assertEquals(-1, world.moonsZ.get(0).position);

        world.simulateStep();

        System.out.println("After 3 steps:");
        System.out.println(world);
        assertEquals(5, world.moonsX.get(0).position);
        assertEquals(-6, world.moonsY.get(0).position);
        assertEquals(-1, world.moonsZ.get(0).position);

        world.simulateStep();

        System.out.println("After 4 steps:");
        System.out.println(world);
        assertEquals(2, world.moonsX.get(0).position);
        assertEquals(-8, world.moonsY.get(0).position);
        assertEquals(0, world.moonsZ.get(0).position);

    }


    @Test
    public void testEnergy() {

        A2019_12.World world = A2019_12.parse(
                "<x=-1, y=0, z=2>\n" +
                "<x=2, y=-10, z=-7>\n" +
                "<x=4, y=-8, z=8>\n" +
                "<x=3, y=5, z=-1>"
        );


        world.simulateSteps(10);

        int energy = world.getTotalEnergy();
        assertEquals(179, energy);
    }

    @Test
    public void testEnergy2() {

        A2019_12.World world = A2019_12.parse(
                "<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>"
        );


        world.simulateSteps(100);

        int energy = world.getTotalEnergy();
        assertEquals(1940, energy);
    }

    @Test
    public void real() {

        A2019_12.World world = A2019_12.parse(
                "<x=0, y=6, z=1>\n" +
                "<x=4, y=4, z=19>\n" +
                "<x=-11, y=1, z=8>\n" +
                "<x=2, y=19, z=15>"
        );


        world.simulateSteps(1000);

        int energy = world.getTotalEnergy();
        assertEquals(14809, energy);
    }

    @Test
    public void testTwoFirstUniverse() {

        A2019_12.World world = A2019_12.parse(
                "<x=-1, y=0, z=2>\n" +
                "<x=2, y=-10, z=-7>\n" +
                "<x=4, y=-8, z=8>\n" +
                "<x=3, y=5, z=-1>"
        );


        long steps = world.findPeriod();

        assertEquals(2772, steps);
    }

    @Test
    public void testTwoSecondUniverse() {

        A2019_12.World world = A2019_12.parse(
                "<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>"
        );


        long steps = world.findPeriod();

        assertEquals(4686774924l, steps);
    }

    @Test
    public void realTwo() {

        A2019_12.World world = A2019_12.parse(
                "<x=0, y=6, z=1>\n" +
                "<x=4, y=4, z=19>\n" +
                "<x=-11, y=1, z=8>\n" +
                "<x=2, y=19, z=15>"
        );


        long steps = world.findPeriod();

        assertEquals(282270365571288l, steps);
    }

}
