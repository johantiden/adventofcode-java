package com.github.johantiden.adventofcode._2021;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class A2021_11Test {

    @Test
    public void a() {
        assertThat(A2021_11.a(A2021_11.EXAMPLE, 10), is(204L));
        assertThat(A2021_11.a(A2021_11.EXAMPLE, 100), is(1656L));

        assertThat(A2021_11.a(A2021_11.REAL, 100), is(1642L));
    }

    @Test
    public void b() {
        assertThat(A2021_11.b(A2021_11.EXAMPLE), is(195L));
        assertThat(A2021_11.b(A2021_11.REAL), is(320L));
    }

    @Test
    public void step() {
        assertThat(A2021_11.step(A2021_09.parse(A2021_11.SMALL_EXAMPLE)).get(2, 2), is(0));
    }
}
