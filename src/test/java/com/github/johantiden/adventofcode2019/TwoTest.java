package com.github.johantiden.adventofcode2019;

import org.junit.Test;

public class TwoTest {

    @Test
    public void testRun() {
        IntcodeComputer.run(new int[]{1,9,10,3,2,3,11,0,99,30,40,50});
    }
}
