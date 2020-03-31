package com.github.johantiden.adventofcode2019;


import java.util.HashMap;
import java.util.Map;

public class IntcodeComputer {
    private static final boolean DEBUG_VERBOSE = true;

    private long relativeBase;
    private final Memory memory;
    private final Input input;
    private final Output output;

    public IntcodeComputer(Memory memory, Input input, Output output) {
        this.memory = memory;
        this.input = input;
        this.output = output;
    }

    public static IntcodeComputer of(long[] memory, Input input, Output output) {
        return new IntcodeComputer(Memory.of(memory), input, output);
    }

    void run() {
        Memory.Address instructionPointer = memory.address(0);
        while (true) {
            Memory.Address nextInstructionPointer = runOneInstruction(instructionPointer);

            if (nextInstructionPointer == null) {
                return;
            }

            instructionPointer = nextInstructionPointer;
        }
    }

    private Memory.Address runOneInstruction(Memory.Address instructionPointer) {
        int opcode = (int) (instructionPointer.read() % 100);
        switch (opcode) {
            case 1:
                return _1add(instructionPointer);
            case 2:
                return _2multiply(instructionPointer);
            case 3:
                return _3readInput(instructionPointer);
            case 4:
                return _4output(instructionPointer);
            case 5:
                return _5jumpIfTrue(instructionPointer);
            case 6:
                return _6jumpIfFalse(instructionPointer);
            case 7:
                return _7lessThan(instructionPointer);
            case 8:
                return _8equals2(instructionPointer);
            case 9:
                return _9offsetRelativeBase(instructionPointer);
            case 99:
                if (DEBUG_VERBOSE) {
                    System.out.println("99 at address " + instructionPointer + ", exiting...");
                }
                return null;
            default:
                throw new IllegalStateException("Illegal opcode:" + opcode);
        }
    }

    private Memory.Address _9offsetRelativeBase(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);
        long value = param1.getValue();
        if (DEBUG_VERBOSE) {
            System.out.println("9: offsetRelativeBase with " + param1 + " to " + (relativeBase+value));
        }
        relativeBase += value;
        return memory.offset(instructionPointer, 2);
    }

    private Memory.Address _8equals2(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);
        Param param2 = parseParameter(instructionPointer, 2);
        Param param3 = parseParameter(instructionPointer, 3);

        long output;
        boolean result = param1.getValue() == param2.getValue();
        if (result) {
            output = 1;
        } else {
            output = 0;
        }

        if (DEBUG_VERBOSE) {
            System.out.println("8: Equals " + param1 + "==" + param2 + " = " + result+ ". Saving "+output+" to " + param3);
        }

        param3.asAddress().write(output);

        return memory.offset(instructionPointer, 4);
    }

    private Memory.Address _7lessThan(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);
        Param param2 = parseParameter(instructionPointer, 2);
        Param param3 = parseParameter(instructionPointer, 3);

        long output;
        boolean result = param1.getValue() < param2.getValue();
        if (result) {
            output = 1;
        } else {
            output = 0;
        }

        if (DEBUG_VERBOSE) {
            System.out.println("7: lessthan " + param1 + "<" + param2 + " = " + result+ ". Saving "+output+" to " + param3);
        }

        param3.asAddress().write(output);

        return memory.offset(instructionPointer, 4);
    }

    private Memory.Address _5jumpIfTrue(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);
        Param param2 = parseParameter(instructionPointer, 2);

        if (param1.getValue() != 0) {

            if (DEBUG_VERBOSE) {
                System.out.println("5: jumpIfTrue " + param1 + " != 0. jumping to " + param2);
            }

            return memory.address(param2.getValue());
        } else {
            if (DEBUG_VERBOSE) {
                System.out.println("5: jumpIfTrue " + param1 + " == 0. no jump, skipping...");
            }

            return memory.offset(instructionPointer, 3);
        }
    }

    private Memory.Address _6jumpIfFalse(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);
        Param param2 = parseParameter(instructionPointer, 2);

        if (param1.getValue() == 0) {
            if (DEBUG_VERBOSE) {
                System.out.println("6: jumpIfFalse " + param1 + " == 0. jumping to " + param2);
            }
            return memory.address(param2.getValue());
        } else {
            if (DEBUG_VERBOSE) {
                System.out.println("6: jumpIfFalse " + param1 + " != 0. no jump, skipping...");
            }
            return memory.offset(instructionPointer, 3);
        }
    }

    private Memory.Address _4output(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);
        long value = param1.getValue();
        if (DEBUG_VERBOSE) {
            System.out.println("4: output " + param1);
        }
        output.write(value);
        return memory.offset(instructionPointer, 2);
    }

    private Memory.Address _3readInput(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);

        long argument;
        try {
            argument = input.read();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for input.");
        }
        Memory.Address target = param1.asAddress();
        if (DEBUG_VERBOSE) {
            System.out.println("3: Input " + argument + ", saving to "+ param1);
        }

        target.write(argument);
        return memory.offset(instructionPointer, 2);

    }

    private Memory.Address _2multiply(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);
        Param param2 = parseParameter(instructionPointer, 2);
        Param param3 = parseParameter(instructionPointer, 3);

        long a = param1.getValue();
        long b = param2.getValue();

        long result = a * b;
        if (DEBUG_VERBOSE) {
            System.out.println("2: Multiply " + param1 + "*" + param2 + " = " + result+ ". Saving to " + param3);
        }

        param3.asAddress().write(result);
        return memory.offset(instructionPointer, 4);
    }

    private Memory.Address _1add(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);
        Param param2 = parseParameter(instructionPointer, 2);
        Param param3 = parseParameter(instructionPointer, 3);

        long a = param1.getValue();
        long b = param2.getValue();

        long result = a + b;

        if (DEBUG_VERBOSE) {
            System.out.println("1: Add " + param1 + "+" + param2 + " = " + result+ ". Saving to " + param3);
        }

        param3.asAddress().write(result);
        return memory.offset(instructionPointer, 4);
    }

    Param parseParameter(Memory.Address instructionPointer, int offset) {
        long instruction = instructionPointer.read();
        int parameterMode = (int) (instruction / ((int)Math.pow(10, offset+1)) % 10);

        long read = read(instructionPointer, offset);
        switch (parameterMode) {
            case 0: return Param.pointer(memory.address(read));
            case 1: return Param.immediate(read);
            case 2: return Param.pointer(memory.address(read + relativeBase));
            default:
                throw new IllegalStateException("No such parameter mode " + parameterMode);
        }
    }

    private long read(Memory.Address instructionPointer, int offset) {
        return memory.offset(instructionPointer, offset).read();
    }

    interface Param {
        static Param immediate(long i) {
            return new Param() {
                @Override
                public long getValue() {
                    return i;
                }

                @Override
                public Memory.Address asAddress() {
                    throw new IllegalStateException("getAddress not supported by immediate params");
                }

                @Override
                public String toString() {
                    return "immediate{"+getValue()+"}";
                }
            };
        }

        static Param pointer(Memory.Address address) {
            return new Param() {
                @Override
                public long getValue() {
                    return address.read();
                }

                @Override
                public Memory.Address asAddress() {
                    return address;
                }

                @Override
                public String toString() {
                    return "pointer{"+address+"}, currently: " + getValue();
                }
            };
        }

        long getValue();

        Memory.Address asAddress();
    }

    public static class Memory {

        private final Map<Long, Long> memory = new HashMap<>();

        public static Memory of(long[] array) {
            Memory memory = new Memory();

            for (int i = 0; i < array.length; i++) {
                memory.memory.put((long) i, array[i]);
            }
            return memory;
        }


        Address address(long i) {
            return new Address() {
                @Override
                public long toLong() {
                    return i;
                }
            };
        }

        public Address offset(Address address, int offset) {
            return new Address() {
                @Override
                public long toLong() {
                    return address.toLong() + offset;
                }
            };
        }

        public long getValue(long address) {
            return memory.getOrDefault(address, 0l);
        }

        abstract class Address {

            abstract long toLong();

            long read() {
                return memory.getOrDefault(toLong(), 0l);
            }

            void write(long value) {
                memory.put(toLong(), value);
            }

            @Override
            public String toString() {
                return "Address{"+ toLong()+"}";
            }
        }
    }


    public interface Input {
        long read() throws InterruptedException;
    }
    public interface Output {
        Output SOUT = System.out::println;

        void write(long output);
    }
}
