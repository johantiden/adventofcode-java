package com.github.johantiden.adventofcode2019;

public class IntcodeComputer {

    private static void add(int[] memory, int instructionPointer) {
        int aAddress = memory[instructionPointer + 1];
        int bAddress = memory[instructionPointer + 2];
        int targetAddress = memory[instructionPointer + 3];
        int a = memory[aAddress];
        int b = memory[bAddress];
        int result = a + b;
        memory[targetAddress] = result;
    }

    private static void multiply(int[] memory, int instructionPointer) {
        int aAddress = memory[instructionPointer + 1];
        int bAddress = memory[instructionPointer + 2];
        int targetAddress = memory[instructionPointer + 3];
        int a = memory[aAddress];
        int b = memory[bAddress];
        int result = a * b;
        memory[targetAddress] = result;
    }

    static void run(int[] memory) {
        int instructionPointer = 0;
        while (true) {
            int opcode = memory[instructionPointer];
            switch (opcode) {
                case 1:
                    add(memory, instructionPointer);
                    instructionPointer += 4;
                    break;
                case 2:
                    multiply(memory, instructionPointer);
                    instructionPointer += 4;
                    break;
                case 99:
                    System.out.println("99 at address " + instructionPointer + ", exiting...");
                    return;
            }
        }
    }
}
