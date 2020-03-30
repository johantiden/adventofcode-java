package com.github.johantiden.adventofcode2019;

import org.junit.Test;


import static org.junit.Assert.*;

public class IntcodeComputerTest {


    @Test
    public void testParseParameterAsPointer() {
        int[] memoryArray = {1002, 4, 3, 4, 33};
        IntcodeComputer.Memory memory = new IntcodeComputer.Memory(memoryArray);
        IntcodeComputer intcodeComputer = new IntcodeComputer(
                memory,
                null,
                null
        );

        IntcodeComputer.Param param = intcodeComputer.parseParameter(memory.address(0), 1);
        int value = param.getValue();
        assertEquals(33, value);
    }

    @Test
    public void testParseParameterAsImmediate() {
        int[] memoryArray = {1002, 4, 3, 4, 33};
        IntcodeComputer.Memory memory = new IntcodeComputer.Memory(memoryArray);
        IntcodeComputer intcodeComputer = new IntcodeComputer(
                memory,
                null,
                null
        );

        IntcodeComputer.Param param = intcodeComputer.parseParameter(memory.address(0), 2);
        assertEquals(3, param.getValue());
    }

    @Test
    public void testImmediate() {

        int[] memoryArray = {1002, 4, 3, 4, 33};
        IntcodeComputer.Memory memory = new IntcodeComputer.Memory(memoryArray);

        IntcodeComputer computer = new IntcodeComputer(
                memory,
                null,
                null
        );
        computer.run();

        assertEquals(99, memoryArray[4]);

    }

    @Test
    public void testNegativeImmediate() {

        int[] memoryArray = {1101,100,-1,4,0};
        IntcodeComputer.Memory memory = new IntcodeComputer.Memory(memoryArray);

        IntcodeComputer computer = new IntcodeComputer(
                memory,
                null,
                null
        );
        computer.run();

        assertEquals(99, memoryArray[4]);

    }

}
