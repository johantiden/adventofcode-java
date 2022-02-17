package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;

public class A2021_06 {

    private static final JList<Integer> EXAMPLE = JList.of(3,4,3,1,2);
    private static final JList<Integer> REAL = JList.of(1,1,1,1,3,1,4,1,4,1,1,2,5,2,5,1,1,1,4,3,1,4,1,1,1,1,1,1,1,2,1,2,4,1,1,1,1,1,1,1,3,1,1,5,1,1,2,1,5,1,1,1,1,1,1,1,1,4,3,1,1,1,2,1,1,5,2,1,1,1,1,4,5,1,1,2,4,1,1,1,5,1,1,1,1,5,1,3,1,1,4,2,1,5,1,2,1,1,1,1,1,3,3,1,5,1,1,1,1,3,1,1,1,4,1,1,1,4,1,4,3,1,1,1,4,1,2,1,1,1,2,1,1,1,1,5,1,1,3,5,1,1,5,2,1,1,1,1,1,4,4,1,1,2,1,1,1,4,1,1,1,1,5,3,1,1,1,5,1,1,1,4,1,4,1,1,1,5,1,1,3,2,2,1,1,1,4,1,3,1,1,1,2,1,3,1,1,1,1,4,1,1,1,1,2,1,4,1,1,1,1,1,4,1,1,2,4,2,1,2,3,1,3,1,1,2,1,1,1,3,1,1,3,1,1,4,1,3,1,1,2,1,1,1,4,1,1,3,1,1,5,1,1,3,1,1,1,1,5,1,1,1,1,1,2,3,4,1,1,1,1,1,2,1,1,1,1,1,1,1,3,2,2,1,3,5,1,1,4,4,1,3,4,1,2,4,1,1,3,1
    );

    public static void main(String[] args) {
        {
            JList<Long> fishes = histogram(REAL);

            //System.out.println("Initial state: " + fishes);
            for (int i = 1; i <= 80; i++) {
                fishes = dayHistogram(fishes);
                //System.out.println("After\t"+i+" days: " + fishes);
            }

            System.out.println("a=" + fishes.reduce(0L, Long::sum));
        }
        {
            JList<Long> fishes = histogram(REAL);

            for (int i = 1; i <= 256; i++) {
                fishes = dayHistogram(fishes);
            }

            System.out.println("b=" + fishes.reduce(0L, Long::sum));
        }

    }

    private static JList<Long> histogram(JList<Integer> input) {
        JList<Long> histogram = JList.repeat(0L, 9);

        while (!input.isEmpty()) {
            Integer head = input.head();
            long count = input.filter(i -> i.equals(head)).size();
            histogram = histogram.with(head, count);
            input = input.filter(i -> !i.equals(head));
        }
        return histogram;
    }

    private static JList<Long> dayHistogram(JList<Long> histogram) {
        long numberOfSpawnsToday = histogram.head();
        return histogram.tail()
                .plus(0L)
                .with(6, i -> i + numberOfSpawnsToday)
                .with(8, numberOfSpawnsToday);
    }
}
