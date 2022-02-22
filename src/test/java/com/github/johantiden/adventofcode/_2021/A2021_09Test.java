package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class A2021_09Test {
    @Test
    public void a() {
        assertThat(A2021_09.a(A2021_09.EXAMPLE), is(26));
//        assertThat(A2021_09.a(A2021_09.REAL), is(539));
    }


    @Test
    public void testParseRow() {
        JList<Integer> actual = A2021_09.parseRow("123");
        assertThat(actual, is(JList.of(1, 2, 3)));
    }
}
