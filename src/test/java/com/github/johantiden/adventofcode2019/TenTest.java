package com.github.johantiden.adventofcode2019;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TenTest {


    public static final String INPUT = ".#.####..#.#...#...##..#.#.##.\n" +
            "..#####.##..#..##....#..#...#.\n" +
            "......#.......##.##.#....##..#\n" +
            "..#..##..#.###.....#.#..###.#.\n" +
            "..#..#..##..#.#.##..###.......\n" +
            "...##....#.##.#.#..##.##.#...#\n" +
            ".##...#.#.##..#.#........#.#..\n" +
            ".##...##.##..#.#.##.#.#.#.##.#\n" +
            "#..##....#...###.#..##.#...##.\n" +
            ".###.###..##......#..#...###.#\n" +
            ".#..#.####.#..#....#.##..#.#.#\n" +
            "..#...#..#.#######....###.....\n" +
            "####..#.#.#...##...##....#..##\n" +
            "##..#.##.#.#..##.###.#.##.##..\n" +
            "..#.........#.#.#.#.......#..#\n" +
            "...##.#.....#.#.##........#..#\n" +
            "##..###.....#.............#.##\n" +
            ".#...#....#..####.#.#......##.\n" +
            "..#..##..###...#.....#...##..#\n" +
            "...####..#.#.##..#....#.#.....\n" +
            "####.#####.#.#....#.#....##.#.\n" +
            "#.#..#......#.........##..#.#.\n" +
            "#....##.....#........#..##.##.\n" +
            ".###.##...##..#.##.#.#...#.#.#\n" +
            "##.###....##....#.#.....#.###.\n" +
            "..#...#......#........####..#.\n" +
            "#....#.###.##.#...#.#.#.#.....\n" +
            ".........##....#...#.....#..##\n" +
            "###....#.........#..#..#.#.#..\n" +
            "##...#...###.#..#.###....#.##.";

    @Test
    public void testParseSimple() {
        Ten.Map map = Ten.parse(

                ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##"
        );

        System.out.println(map);

        assertEquals(false, map.get(0, 0));
        assertEquals(true, map.get(1, 0));
        assertEquals(false, map.get(0, 1));
    }

    @Test
    public void testLightSimple() {
        Ten.Map map = Ten.parse(

                "..........\n" +
                "...#......\n" +
                "...#......\n" +
                ".####.....\n" +
                "..#.......\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                ".........."
        );



        Ten.Map lightMap = map.getLightMap(new Position(0, 0));


        Ten.Map expectedLightMap = Ten.parse(

                "##########\n" +
                "##########\n" +
                "######.###\n" +
                "#########.\n" +
                "####.#.###\n" +
                "#####.####\n" +
                "##...#.#..\n" +
                "#######.##\n" +
                "####.###.#\n" +
                "###.##.##."
        );


        System.out.println("map:");
        System.out.println(map);
        System.out.println("lightMap:");
        System.out.println(lightMap);

        assertEquals(expectedLightMap, lightMap);
    }

    @Test
    public void testCountVisible() {
        Ten.Map map = Ten.parse(
                ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##"
        );

        HashMap<Position, Integer> counts = Ten.countVisibleAsteroidsFromAllAsteroids(map);

        String s = toString(counts, map.width, map.height);
        System.out.println(s);

        boolean a = true;

    }

    @Test
    public void testFindBestSimple() {
        Ten.Map map = Ten.parse(
            ".#..#\n" +
            ".....\n" +
            "#####\n" +
            "....#\n" +
            "...##"
        );
        Map.Entry<Position, Integer> best = Ten.findBestSpotForStation(map);
        assertEquals(new Position(3, 4), best.getKey());
    }

    @Test
    public void testFindBestSimple2() {
        Ten.Map map = Ten.parse(
            "......#.#.\n" +
                    "#..#.#....\n" +
                    "..#######.\n" +
                    ".#.#.###..\n" +
                    ".#..#.....\n" +
                    "..#....#.#\n" +
                    "#..#....#.\n" +
                    ".##.#..###\n" +
                    "##...#..#.\n" +
                    ".#....####"
        );
        Map.Entry<Position, Integer> best = Ten.findBestSpotForStation(map);
        assertEquals(new Position(5, 8), best.getKey());
    }

    @Test
    public void testFindBestSimple3() {
        Ten.Map map = Ten.parse(
            "#.#...#.#.\n" +
                    ".###....#.\n" +
                    ".#....#...\n" +
                    "##.#.#.#.#\n" +
                    "....#.#.#.\n" +
                    ".##..###.#\n" +
                    "..#...##..\n" +
                    "..##....##\n" +
                    "......#...\n" +
                    ".####.###."
        );
        Map.Entry<Position, Integer> best = Ten.findBestSpotForStation(map);
        assertEquals(new Position(1, 2), best.getKey());
    }

    @Test
    public void testFindBestSimple4() {
        Ten.Map map = Ten.parse(
            ".#..#..###\n" +
                    "####.###.#\n" +
                    "....###.#.\n" +
                    "..###.##.#\n" +
                    "##.##.#.#.\n" +
                    "....###..#\n" +
                    "..#.#..#.#\n" +
                    "#..#.#.###\n" +
                    ".##...##.#\n" +
                    ".....#.#.."
        );
        Map.Entry<Position, Integer> best = Ten.findBestSpotForStation(map);
        assertEquals(new Position(6, 3), best.getKey());
        assertEquals(41, (int)best.getValue());
    }

    @Test
    public void testFindBestSimple5() {
        Ten.Map map = Ten.parse(
            ".#..##.###...#######\n" +
                    "##.############..##.\n" +
                    ".#.######.########.#\n" +
                    ".###.#######.####.#.\n" +
                    "#####.##.#.##.###.##\n" +
                    "..#####..#.#########\n" +
                    "####################\n" +
                    "#.####....###.#.#.##\n" +
                    "##.#################\n" +
                    "#####.##.###..####..\n" +
                    "..######..##.#######\n" +
                    "####.##.####...##..#\n" +
                    ".#####..#.######.###\n" +
                    "##...#.##########...\n" +
                    "#.##########.#######\n" +
                    ".####.#.###.###.#.##\n" +
                    "....##.##.###..#####\n" +
                    ".#.#.###########.###\n" +
                    "#.#.#.#####.####.###\n" +
                    "###.##.####.##.#..##"
        );
        Map.Entry<Position, Integer> best = Ten.findBestSpotForStation(map);
        assertEquals(new Position(11, 13), best.getKey());
    }

    @Test
    public void real() {
        Ten.Map map = Ten.parse(INPUT);
        Map.Entry<Position, Integer> best = Ten.findBestSpotForStation(map);
        assertEquals(new Position(22, 25), best.getKey());
        assertEquals(286, (int)best.getValue());
    }

    @Test
    public void testPartTwoFromText() {

        Ten.Map map = Ten.parse(
                ".#....#####...#..\n" +
                "##...##.#####..##\n" +
                "##...#...#.#####.\n" +
                "..#.....X...###..\n" +
                "..#.#.....#....##"
        );

        Position laser = new Position(8, 3);

        List<Position> destroyed = Ten.laserTime(map, laser);

        for (int i = 0; i < destroyed.size(); i+=9) {
            List<Position> toPrint = destroyed.subList(i, Math.min(i+9, destroyed.size()));
            String s = toString(map, toPrint, map.width, map.height);

            System.out.println("map:");
            System.out.println(map);

            System.out.println("visible:");
            System.out.println(Ten.getVisibleAsteroidsFromAsMap(laser, map));

            System.out.println("destroyed:");
            System.out.println(s);

            toPrint.forEach(d -> map.set(d, false));
        }

        assertTrue(map.isEmpty());
    }

    @Test
    public void testPartTwoLarge() {

        Ten.Map map = Ten.parse(
".#..##.###...#######\n" +
"##.############..##.\n" +
".#.######.########.#\n" +
".###.#######.####.#.\n" +
"#####.##.#.##.###.##\n" +
"..#####..#.#########\n" +
"####################\n" +
"#.####....###.#.#.##\n" +
"##.#################\n" +
"#####.##.###..####..\n" +
"..######..##.#######\n" +
"####.##.####...##..#\n" +
".#####..#.######.###\n" +
"##...#.##########...\n" +
"#.##########.#######\n" +
".####.#.###.###.#.##\n" +
"....##.##.###..#####\n" +
".#.#.###########.###\n" +
"#.#.#.#####.####.###\n" +
"###.##.####.##.#..##"
        );

        Position laser = new Position(11, 13);

        List<Position> destroyed = Ten.laserTime(map, laser);




        for (int i = 0; i < destroyed.size(); i+=9) {
            List<Position> toPrint = destroyed.subList(i, Math.min(i+9, destroyed.size()));
            String s = toString(map, toPrint, map.width, map.height);

            System.out.println("map:");
            System.out.println(map);

            System.out.println("visible:");
            System.out.println(Ten.getVisibleAsteroidsFromAsMap(laser, map));

            System.out.println("destroyed:");
            System.out.println(s);

            toPrint.forEach(d -> map.set(d, false));
        }

        assertTrue(map.isEmpty());


        assertEquals(new Position(11,12), destroyed.get(0));
        assertEquals(new Position(12,1), destroyed.get(1));
        assertEquals(new Position(12,2), destroyed.get(2));
        assertEquals(new Position(12,8), destroyed.get(9));
        assertEquals(new Position(16,0), destroyed.get(19));
        assertEquals(new Position(16,9), destroyed.get(49));
        assertEquals(new Position(10,16), destroyed.get(99));
        assertEquals(new Position(8,2), destroyed.get(199));

    }

    public String toString(Ten.Map map, List<Position> destroyed, int width, int height) {
        String s = "";

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                Position position = new Position(x, y);
                int i = destroyed.indexOf(position);

                if (i >= 0) {
                    s += (i+1);
                } else {
                    s += (map.get(position) ? "#" : ".");
                }
            }
            s += "\n";
        }

        return s;
    }

    public String toString(HashMap<Position, Integer> counts, int width, int height) {
        String s = "";

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Integer count = counts.get(new Position(x, y));
                s += (count == null ? "." : count);
            }
            s += "\n";
        }

        return s;
    }


    @Test
    public void realPartTwo() {

        Ten.Map map = Ten.parse(INPUT);

        List<Position> destroyedOrder = Ten.laserTime(map, new Position(22, 25));

        Position actual = destroyedOrder.get(200);

        int answer = actual.x * 100 + actual.y;

        assertEquals(new Position(0, 0), actual);
    }
}
