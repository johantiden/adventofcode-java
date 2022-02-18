package com.github.johantiden.adventofcode._2021;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class A2021_01Test {

    @Test
    public void testResults() {
        assertThat(A2021_01.a(A2021_01.EXAMPLE), is(7));
        assertThat(A2021_01.a(A2021_01.REAL), is(1387));

        assertThat(A2021_01.b(A2021_01.EXAMPLE), is(5));
        assertThat(A2021_01.b(A2021_01.REAL), is(1362));
    }
}
