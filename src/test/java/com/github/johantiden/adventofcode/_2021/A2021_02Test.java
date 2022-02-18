package com.github.johantiden.adventofcode._2021;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class A2021_02Test {

    @Test
    public void a() {
        assertThat(A2021_02.a(A2021_02.EXAMPLE), is(150));
        assertThat(A2021_02.a(A2021_02.REAL), is(1499229));
    }

    @Test
    public void b() {
        assertThat(A2021_02.b(A2021_02.EXAMPLE), is(900));
        assertThat(A2021_02.b(A2021_02.REAL), is(1340836560));
    }
}
