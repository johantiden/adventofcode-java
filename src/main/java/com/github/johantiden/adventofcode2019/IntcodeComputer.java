package com.github.johantiden.adventofcode2019;


import jdk.internal.util.xml.impl.Input;

public class IntcodeComputer {
    private static final boolean DEBUG_VERBOSE = false;

    private final Memory memory;
    private final Input input;
    private final Output output;

    public IntcodeComputer(Memory memory, Input input, Output output) {
        this.memory = memory;
        this.input = input;
        this.output = output;
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
        int opcode = instructionPointer.read() % 100;
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
            case 99:
                if (DEBUG_VERBOSE) {
                    System.out.println("99 at address " + instructionPointer + ", exiting...");
                }
                return null;
            default:
                throw new IllegalStateException("Illegal opcode:" + opcode);
        }
    }

    private Memory.Address _8equals2(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);
        Param param2 = parseParameter(instructionPointer, 2);
        Param param3 = parseParameter(instructionPointer, 3);

        int output;
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

        int output;
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
        int value = param1.getValue();
        if (DEBUG_VERBOSE) {
            System.out.println("4: output " + param1);
        }
        output.write(value);
        return memory.offset(instructionPointer, 2);
    }

    private Memory.Address _3readInput(Memory.Address instructionPointer) {
        Param param1 = parseParameter(instructionPointer, 1);

        Integer argument = null;
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

        int a = param1.getValue();
        int b = param2.getValue();

        int result = a * b;
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

        int a = param1.getValue();
        int b = param2.getValue();

        int result = a + b;

        if (DEBUG_VERBOSE) {
            System.out.println("1: Add " + param1 + "+" + param2 + " = " + result+ ". Saving to " + param3);
        }

        param3.asAddress().write(result);
        return memory.offset(instructionPointer, 4);
    }

    Param parseParameter(Memory.Address instructionPointer, int offset) {
        int instruction = instructionPointer.read();
        int parameterMode =  (instruction / ((int)Math.pow(10, offset+1)) % 10);

        int read = read(instructionPointer, offset);
        switch (parameterMode) {
            case 0:
                return Param.pointer(memory.address(read));
            case 1: return Param.immediate(read);
            default:
                throw new IllegalStateException("No such parameter mode " + parameterMode);
        }
    }

    private int read(Memory.Address instructionPointer, int offset) {
        return memory.offset(instructionPointer, offset).read();
    }

    interface Param {
        static Param immediate(int i) {
            return new Param() {
                @Override
                public int getValue() {
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
                public int getValue() {
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

        int getValue();

        Memory.Address asAddress();
    }

    static class Memory {

        private final int[] memory;

        Memory(int[] memory) {
            this.memory = memory;
        }

        Address address(int i) {
            return new Address() {
                @Override
                public int toInt() {
                    return i;
                }
            };
        }

        public Address offset(Address address, int offset) {
            return new Address() {
                @Override
                public int toInt() {
                    return address.toInt() + offset;
                }
            };
        }

        abstract class Address {

            abstract int toInt();

            int read() {
                return memory[toInt()];
            }

            void write(int value) {
                memory[toInt()] = value;
            }

            @Override
            public String toString() {
                return "Address{"+toInt()+"}";
            }
        }
    }


    public interface Input {
        int read() throws InterruptedException;
    }
    public interface Output {
        Output SOUT = System.out::println;

        void write(int output);
    }
}
