package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;
import com.github.johantiden.adventofcode.common.Lists;
import com.github.johantiden.adventofcode.common.Matrix;

public class A2021_09 {

    static final String EXAMPLE =
"""
2199943210
3987894921
9856789892
8767896789
9899965678
""";


    public static long a(String input) {
        Matrix<Integer> heightMap = parse(input);


        return -1;
    }

    private static Matrix<Integer> parse(String input) {
        JList<JList<Integer>> listList = JList.ofArray(input.split("\n"))
                .map(A2021_09::parseRow);
        return Matrix.of(listList);
    }

    static JList<Integer> parseRow(String row) {
        return Lists.charactersOf(row)
                .map(String::valueOf)
                .map(Integer::parseUnsignedInt);
    }
}
