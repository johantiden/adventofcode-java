package com.github.johantiden.adventofcode.common;

public class Lists {
    public static <T> JList<JList<T>> slidingWindows(JList<T> list, int windowSize) {
        JList<JList<T>> windows = JList.<JList<T>>of();
        for (int i = 0; i < list.size() - windowSize + 1; i++) {
            JList<T> window = JList.of();
            for (int j = 0; j < windowSize; j++) {
                window = window.plus(list.get(i + j));
            }
            windows = windows.plus(window);
        }
        return windows;
    }

    public static <T> JList<Pair<T, T>> slidingWindowPairs(JList<T> list) {
        return slidingWindows(list, 2)
                .map(p -> Pair.of(p.get(0), p.get(1)));
    }
}
