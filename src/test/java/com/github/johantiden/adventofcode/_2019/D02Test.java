package com.github.johantiden.adventofcode._2019;

import com.github.johantiden.adventofcode._2019.IntcodeComputer;
import org.junit.Test;

public class D02Test {

    @Test
    public void testRun() {
        new IntcodeComputer(IntcodeComputer.Memory.of(new long[]{1,9,10,3,2,3,11,0,99,30,40,50}), null, null).run();
    }
}
