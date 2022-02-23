package com.github.johantiden.adventofcode._2021;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class A2021_04Test {

    @Test
    public void a() {
        assertThat(A2021_04.a(A2021_04.EXAMPLE), is(4512));
        assertThat(A2021_04.a(A2021_04.REAL), is(41668));
    }

    @Test
    public void b() {
        assertThat(A2021_04.b(A2021_04.EXAMPLE), is(1924));
        assertThat(A2021_04.b(A2021_04.REAL), is(10478));
    }
}
