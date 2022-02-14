package com.github.johantiden.adventofcode._2019;

import com.github.johantiden.adventofcode._2019.D04;
import org.junit.Test;

import static org.junit.Assert.*;

public class D04Test {

    @Test
    public void name() {


        assertTrue(D04.PartTwo.hasExactTwoAdjacentDigitsSame(111122));
        assertFalse(D04.PartTwo.hasExactTwoAdjacentDigitsSame(1234222));

    }
}
