package com.github.johantiden.adventofcode2019;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class D05Test {

    @Test
    public void testEquals8() {
        long[] program = {3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8};

        assertProgramOutput(program, 8, 1);
        assertProgramOutput(program, 7, 0);
        assertProgramOutput(program, -8, 0);
        assertProgramOutput(program, 9, 0);
    }

    @Test
    public void testJumpPositionModeZero() {
        long[] program = {3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9};

        assertProgramOutput(program, 0, 0);
        assertProgramOutput(program, 17, 1);

    }

    @Test
    public void testJumpPositionModeZeroImmediate() {
        long[] program = {3,3,1105,-1,9,1101,0,0,12,4,12,99,1};

        assertProgramOutput(program, 0, 0);
        assertProgramOutput(program, 17, 1);

    }

    @Test
    public void testLarge() {
        long[] program = {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};

        assertProgramOutput(program, 7, 999);
        assertProgramOutput(program, 8, 1000);
        assertProgramOutput(program, 9, 1001);

    }

    private static void assertProgramOutput(long[] program, long input, long expectedOutput) {
        IntcodeComputer.Memory memory = IntcodeComputer.Memory.of(program);

        List<Long> output = new ArrayList<>();
        IntcodeComputer computer = new IntcodeComputer(memory, IntcodeComputer.Input.withSupplier(() -> input), output::add);

        computer.run();

        assertEquals(expectedOutput, (long) output.get(0));
    }

}
