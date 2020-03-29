package com.github.johantiden.adventofcode2019;

import org.junit.Test;

import static org.junit.Assert.*;

public class FourTest {

    @Test
    public void name() {


        assertTrue(Four.PartTwo.hasExactTwoAdjacentDigitsSame(111122));
        assertFalse(Four.PartTwo.hasExactTwoAdjacentDigitsSame(1234222));

    }
}
