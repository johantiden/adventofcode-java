package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;
import com.github.johantiden.adventofcode.common.JMap;
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
        assertThat(A2021_08.b(A2021_08.EXAMPLE_ONE_ROW), is(5353L));
        assertThat(A2021_08.b(A2021_08.EXAMPLE), is(61229L));
        assertThat(A2021_08.b(A2021_08.REAL), is(1084606L));
    }

    @Test
    public void testDecode() {
        JMap<String, Integer> dictionary = JMap.<String, Integer>empty()
                .with("acedgfb", 8)
                .with("cdfbe", 5)
                .with("gcdfa", 2)
                .with("fbcad", 3)
                .with("dab", 7)
                .with("cefabd", 9)
                .with("cdfgeb", 6)
                .with("eafb", 4)
                .with("cagedb", 0)
                .with("ab", 1);
        JList<String> signal = JList.of("cdfeb", "fcadb", "cdfeb", "cdbaf");
        long actual = A2021_08.decode(dictionary, signal);
        assertThat(actual, is(5353L));
    }

    @Test
    public void testContainsAll() {
        boolean actual = JList.of('f', 'b', 'c', 'a', 'd').containsAll(JList.of('a', 'b'));
        assertThat(actual, is(true));
    }
}
