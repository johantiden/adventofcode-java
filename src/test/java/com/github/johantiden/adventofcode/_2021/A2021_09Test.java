package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class A2021_09Test {
    @Test
    public void a() {
        assertThat(A2021_09.countLows(A2021_09.EXAMPLE), is(4L));

        assertThat(A2021_09.a(A2021_09.EXAMPLE), is(15L));
        assertThat(A2021_09.a(A2021_09.REAL), is(417L));
    }

    @Test
    public void b() {
        assertThat(A2021_09.b(A2021_09.EXAMPLE), is(1134L));
        assertThat(A2021_09.b(A2021_09.REAL), is(1148965L));
    }

    @Test
    public void testTrivialInput() {
        // There are no neighbors, so this is the lowest
        assertThat(A2021_09.countLows("1"), is(1L));
    }

    @Test
    public void testParseRow() {
        JList<Integer> actual = A2021_09.parseRow("123");
        assertThat(actual, is(JList.of(1, 2, 3)));
    }
}
