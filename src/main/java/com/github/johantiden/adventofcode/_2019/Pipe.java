package com.github.johantiden.adventofcode._2019;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Pipe implements IntcodeComputer.Input, IntcodeComputer.Output{

    private final BlockingQueue<Long> buffer = new LinkedBlockingQueue<>();

    @Override
    public long read() throws InterruptedException {
        return buffer.take();
    }

    @Override
    public void write(long output) {
        buffer.offer(output);
    }

    @Override
    public boolean hasNext() {
        return buffer.peek() != null;
    }
}
