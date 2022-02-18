package com.github.johantiden.adventofcode._2021;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class A2021_06Test {

    @Test
    public void a() {
        assertThat(A2021_06.a(A2021_06.EXAMPLE), is(5934L));
        assertThat(A2021_06.a(A2021_06.REAL), is(388419L));
    }

    @Test
    public void b() {
        assertThat(A2021_06.b(A2021_06.EXAMPLE), is(26984457539L));
        assertThat(A2021_06.b(A2021_06.REAL), is(1740449478328L));
    }
}
