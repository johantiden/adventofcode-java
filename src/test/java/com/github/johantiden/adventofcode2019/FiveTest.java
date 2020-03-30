package com.github.johantiden.adventofcode2019;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiveTest {

    @Test
    public void testEquals8() {
        int[] program = {3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8};

        assertProgramOutput(program, 8, 1);
        assertProgramOutput(program, 7, 0);
        assertProgramOutput(program, -8, 0);
        assertProgramOutput(program, 9, 0);
    }

    @Test
    public void testJumpPositionModeZero() {
        int[] program = {3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9};

        assertProgramOutput(program, 0, 0);
        assertProgramOutput(program, 17, 1);

    }

    @Test
    public void testJumpPositionModeZeroImmediate() {
        int[] program = {3,3,1105,-1,9,1101,0,0,12,4,12,99,1};

        assertProgramOutput(program, 0, 0);
        assertProgramOutput(program, 17, 1);

    }

    @Test
    public void testLarge() {
        int[] program = {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};

        assertProgramOutput(program, 7, 999);
        assertProgramOutput(program, 8, 1000);
        assertProgramOutput(program, 9, 1001);

    }

    private static void assertProgramOutput(int[] program, int input, int expectedOutput) {
        IntcodeComputer.Memory memory = new IntcodeComputer.Memory(program.clone());

        List<Integer> output = new ArrayList<>();
        IntcodeComputer computer = new IntcodeComputer(memory, () -> input, output::add);

        computer.run();

        assertEquals(expectedOutput, (int) output.get(0));
    }

}
