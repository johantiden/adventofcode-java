package com.github.johantiden.adventofcode._2019;

import com.github.johantiden.adventofcode._2019.D01;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D01Test {

    @Test
    public void first() {
        assertEquals(2, D01.requiredFuel(12));
        assertEquals(2, D01.requiredFuel(14));
        assertEquals(654, D01.requiredFuel(1969));
        assertEquals(33583, D01.requiredFuel(100756));
    }

    @Test
    public void second() {
        assertEquals(0, D01.PartTwo.extraFuelForFuel(2));
        assertEquals(966, D01.PartTwo.extraFuelForFuel(1969));
        assertEquals(50346, D01.PartTwo.extraFuelForFuel(100756));

    }
}
