package com.github.johantiden.adventofcode._2019;

import org.junit.Test;

import static org.junit.Assert.*;

public class D04Test {

    @Test
    public void name() {


        assertTrue(A2019_04.PartTwo.hasExactTwoAdjacentDigitsSame(111122));
        assertFalse(A2019_04.PartTwo.hasExactTwoAdjacentDigitsSame(1234222));

    }
}
