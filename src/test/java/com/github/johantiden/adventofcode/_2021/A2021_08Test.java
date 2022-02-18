package com.github.johantiden.adventofcode._2021;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class A2021_08Test {

    @Test
    public void a() {
        assertThat(A2021_08.a(A2021_08.EXAMPLE), is(26));
        assertThat(A2021_08.a(A2021_08.REAL), is(539));
    }

    @Test
    public void b() {
        assertThat(A2021_08.b(A2021_08.EXAMPLE), is(61229));
//        assertThat(A2021_08.b(A2021_08.REAL), is(94862124));
    }
}
