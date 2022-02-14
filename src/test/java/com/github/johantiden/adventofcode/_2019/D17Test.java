package com.github.johantiden.adventofcode._2019;

import com.github.johantiden.adventofcode._2019.D17;
import com.github.johantiden.adventofcode._2019.Position;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class D17Test {


    @Test
    public void testIntersections() {
        String s = "..#..........\n" +
                "..#..........\n" +
                "#######...###\n" +
                "#.#...#...#.#\n" +
                "#############\n" +
                "..#...#...#..\n" +
                "..#####...^..";

        D17.Map map = D17.Map.parse(s);

        List<Position> actual = D17.findIntersections(map);

        List<Position> expected = Lists.newArrayList(
                new Position(2, 2),
                new Position(2, 4),
                new Position(6, 4),
                new Position(10, 4)
        );

        assertThat(actual, is(expected));
    }

    @Test
    public void testIntersectionsHashCode() {
        String s = "..#..........\n" +
                "..#..........\n" +
                "#######...###\n" +
                "#.#...#...#.#\n" +
                "#############\n" +
                "..#...#...#..\n" +
                "..#####...^..";

        D17.Map map = D17.Map.parse(s);

        List<Position> intersections = D17.findIntersections(map);

        int actual = D17.hashIntersections(intersections);

        assertThat(actual, is(76));
    }

    @Test
    public void testCompressForwards() {
        ImmutableList<String> of = ImmutableList.of("A", "1", "2", "B");

        ImmutableList<String> actual = D17.Part2.compressForwards(of);
        ImmutableList<String> expected = ImmutableList.of("A", "3", "B");
        assertThat(actual, is(expected));
    }

    @Test
    public void testCompressForwards2() {
        ImmutableList<String> of = ImmutableList.of("A", "1", "2", "4", "B");

        ImmutableList<String> actual = D17.Part2.compressForwards(of);
        ImmutableList<String> expected = ImmutableList.of("A", "7", "B");
        assertThat(actual, is(expected));
    }

    @Test
    public void testCompressForwards3() {
        ImmutableList<String> of = ImmutableList.of("A", "1", "2", "4");

        ImmutableList<String> actual = D17.Part2.compressForwards(of);
        ImmutableList<String> expected = ImmutableList.of("A", "7");
        assertThat(actual, is(expected));
    }

    @Test
    public void testCompressForwards4() {
        ImmutableList<String> of = ImmutableList.of("A", "1", "2", "4", "B", "3");

        ImmutableList<String> actual = D17.Part2.compressForwards(of);
        ImmutableList<String> expected = ImmutableList.of("A", "7", "B", "3");
        assertThat(actual, is(expected));
    }

    @Test
    public void testAddCommas() {
        String actual = D17.Part2.addCommas("LR11L");

        assertThat(actual, is("L,R,11,L"));
    }


    @Test
    public void testSolver() throws InterruptedException {

        D17.Map map = D17.Map.parse(
                "#######...#####\n" +
                "#.....#...#...#\n" +
                "#.....#...#...#\n" +
                "......#...#...#\n" +
                "......#...###.#\n" +
                "......#.....#.#\n" +
                "^########...#.#\n" +
                "......#.#...#.#\n" +
                "......#########\n" +
                "........#...#..\n" +
                "....#########..\n" +
                "....#...#......\n" +
                "....#...#......\n" +
                "....#...#......\n" +
                "....#####......");

        System.out.println(map);
        BlockingQueue<D17.Map> solutions = new LinkedBlockingQueue<>();

        D17.Part2.produceAllSolutions(solutions, map);

        D17.Part2.consumeSolutions(solutions);




    }
}
