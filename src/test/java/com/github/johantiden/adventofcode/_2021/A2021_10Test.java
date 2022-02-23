package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.Lists;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class A2021_10Test {

    @Test
    public void a() {
        assertThat(A2021_10.a(A2021_10.EXAMPLE), is(26397L));
        assertThat(A2021_10.a(A2021_10.REAL), is(268845L));
    }

    @Test
    public void b() {
        assertThat(A2021_10.b(A2021_10.EXAMPLE), is(288957L));
        assertThat(A2021_10.b(A2021_10.REAL), is(4038824534L));
    }

    @Test
    public void asdf() {
        assertThat(A2021_10.findCorruptedCharacter("{([(<{}[<>[]}>{[]{[(<()>"), is(Optional.of('}')));

        assertThat(A2021_10.findCorruptedCharacter("(]"), is(Optional.of(']')));
        assertThat(A2021_10.findCorruptedCharacter("{()()()>"), is(Optional.of('>')));
        assertThat(A2021_10.findCorruptedCharacter("(((()))}"), is(Optional.of('}')));
        assertThat(A2021_10.findCorruptedCharacter("<([]){()}[{}])"), is(Optional.of(')')));
    }

    @Test
    public void testScore() {
        assertThat(A2021_10.score(Lists.charactersOf("}}]])})]")), is(288957L));
    }
}
