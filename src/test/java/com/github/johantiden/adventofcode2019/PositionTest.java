package com.github.johantiden.adventofcode2019;

import org.junit.Test;

import static com.github.johantiden.adventofcode2019.Position.ZERO;
import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void testGetAngle() {


        assertEquals(Math.PI*0/4, Position.getAngle(ZERO, new Position(10, 0)), 0.00001);
        assertEquals(Math.PI*1/4, Position.getAngle(ZERO, new Position(10, 10)), 0.00001);
        assertEquals(Math.PI*2/4, Position.getAngle(ZERO, new Position(0, 10)), 0.00001);
        assertEquals(Math.PI*3/4, Position.getAngle(ZERO, new Position(-10, 10)), 0.00001);
        assertEquals(Math.PI*4/4, Position.getAngle(ZERO, new Position(-10, 0)), 0.00001);
        assertEquals(Math.PI*5/4, Position.getAngle(ZERO, new Position(-10, -10)), 0.00001);
        assertEquals(Math.PI*6/4, Position.getAngle(ZERO, new Position(0, -10)), 0.00001);
        assertEquals(Math.PI*7/4, Position.getAngle(ZERO, new Position(10, -10)), 0.00001);



    }

    @Test
    public void testProperMod() {
        assertEquals(2, Position.properMod(5, 3), 0.000001);
        assertEquals(1, Position.properMod(-5, 3), 0.000001);
        assertEquals(0, Position.properMod(0, 3), 0.000001);
        assertEquals(2, Position.properMod(-1, 3), 0.000001);
        assertEquals(2, Position.properMod(17, 3), 0.000001);
    }
}
