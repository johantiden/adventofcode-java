package com.github.johantiden.adventofcode._2019;

import com.github.johantiden.adventofcode._2019.IntcodeComputer;
import org.junit.Test;


import static org.junit.Assert.*;

public class IntcodeComputerTest {


    @Test
    public void testParseParameterAsPointer() {
        long[] memoryArray = {1002, 4, 3, 4, 33};
        IntcodeComputer.Memory memory = IntcodeComputer.Memory.of(memoryArray);
        IntcodeComputer intcodeComputer = new IntcodeComputer(
                memory,
                null,
                null
        );

        IntcodeComputer.Param param = intcodeComputer.parseParameter(memory.address(0), 1);
        long value = param.getValue();
        assertEquals(33, value);
    }

    @Test
    public void testParseParameterAsImmediate() {
        long[] memoryArray = {1002, 4, 3, 4, 33};
        IntcodeComputer.Memory memory = IntcodeComputer.Memory.of(memoryArray);
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

        IntcodeComputer.Memory memory = IntcodeComputer.Memory.of(new long[]{1002, 4, 3, 4, 33});

        IntcodeComputer computer = new IntcodeComputer(
                memory,
                null,
                null
        );
        computer.run();

        assertEquals(99, memory.getValue(4));
    }

    @Test
    public void testNegativeImmediate() {

        IntcodeComputer.Memory memory = IntcodeComputer.Memory.of(new long[]{1101,100,-1,4,0});

        IntcodeComputer computer = new IntcodeComputer(
                memory,
                null,
                null
        );
        computer.run();

        assertEquals(99, memory.getValue(4));

    }

}
