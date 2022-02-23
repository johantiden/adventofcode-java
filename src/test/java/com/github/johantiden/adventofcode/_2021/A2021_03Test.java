package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static com.github.johantiden.adventofcode._2021.A2021_03.EXAMPLE;
import static com.github.johantiden.adventofcode._2021.A2021_03.REAL;
import static com.github.johantiden.adventofcode._2021.A2021_03.calculateC02ScrubberRating;
import static com.github.johantiden.adventofcode._2021.A2021_03.calculateEpsilon;
import static com.github.johantiden.adventofcode._2021.A2021_03.calculateGamma;
import static com.github.johantiden.adventofcode._2021.A2021_03.calculateLifeSupportRating;
import static com.github.johantiden.adventofcode._2021.A2021_03.calculateOxygenGeneratorRating;
import static com.github.johantiden.adventofcode._2021.A2021_03.calculatePowerConsumption;
import static com.github.johantiden.adventofcode._2021.A2021_03.parse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class A2021_03Test {

    @Test
    public void testCalculateGamma() {
        assertThat(calculateGamma(parse(EXAMPLE)), is(22));
        assertThat(calculateGamma(parse(REAL)), is(3529));
    }

    @Test
    public void testCalculateEpsilon() {
        assertThat(calculateEpsilon(parse(EXAMPLE)), is(9));
        assertThat(calculateEpsilon(parse(REAL)), is(566));
    }

    @Test
    public void testAnswerA() {
        assertThat(calculatePowerConsumption(parse(EXAMPLE)), is(198));
        assertThat(calculatePowerConsumption(parse(REAL)), is(1997414));
    }

    @Test
    public void testMedianOfTwoItems() {
        JList<Boolean> list = JList.of(true, false);
        boolean median = list.median(Comparator.comparing(i -> i));
        assertThat(median, is(true));
    }

    @Test
    public void testCalculateOxygenGeneratorRating() {
        assertThat(calculateOxygenGeneratorRating(parse(EXAMPLE)), is(23));
        assertThat(calculateOxygenGeneratorRating(parse(REAL)), is(3573));
    }

    @Test
    public void testCalculateC02ScrubberRating() {
        assertThat(calculateC02ScrubberRating(parse(EXAMPLE)), is(10));
        assertThat(calculateC02ScrubberRating(parse(REAL)), is(289));
    }

    @Test
    public void testAnswerB() {
        assertThat(calculateLifeSupportRating(parse(EXAMPLE)), is(230));
        assertThat(calculateLifeSupportRating(parse(REAL)), is(1032597));
    }
}
