package com.github.johantiden.adventofcode._2021;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
public class A2021_05Test {

    @Test
    public void a() {
        assertThat(A2021_05.a(A2021_05.EXAMPLE), is(5));
        assertThat(A2021_05.a(A2021_05.REAL), is(5690));
    }

    @Test
    public void b() {
        assertThat(A2021_05.b(A2021_05.EXAMPLE), is(12));
        assertThat(A2021_05.b(A2021_05.REAL), is(17741));
    }
}
