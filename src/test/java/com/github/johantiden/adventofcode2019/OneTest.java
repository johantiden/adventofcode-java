package com.github.johantiden.adventofcode2019;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OneTest {

    @Test
    public void first() {
        assertEquals(2, One.requiredFuel(12));
        assertEquals(2, One.requiredFuel(14));
        assertEquals(654, One.requiredFuel(1969));
        assertEquals(33583, One.requiredFuel(100756));
    }

    @Test
    public void second() {
        assertEquals(0, One.PartTwo.extraFuelForFuel(2));
        assertEquals(966, One.PartTwo.extraFuelForFuel(1969));
        assertEquals(50346, One.PartTwo.extraFuelForFuel(100756));

    }
}
