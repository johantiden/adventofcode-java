package com.github.johantiden.adventofcode._2019;

public class Spy extends Pipe {
    private final Pipe inner;

    public Spy(Pipe inner) {
        this.inner = inner;
    }

    @Override
    public long read() throws InterruptedException {
        return inner.read();
    }

    @Override
    public void write(long output) {
        char c = (char) output;
        System.out.print(c);

        inner.write(output);
    }
}
