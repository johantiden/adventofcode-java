package com.github.johantiden.adventofcode._2021;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class A2021_07Test {

    @Test
    public void a() {
        assertThat(A2021_07.a(A2021_07.EXAMPLE), is(37));
        assertThat(A2021_07.a(A2021_07.REAL), is(344138));
    }

    @Test
    public void b() {
        assertThat(A2021_07.b(A2021_07.EXAMPLE), is(168));
        assertThat(A2021_07.b(A2021_07.REAL), is(94862124));
    }
}
