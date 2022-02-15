package com.github.johantiden.adventofcode.common;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JMap<K, V> {

    private final Map<K, V> inner;

    public JMap(Map<K, V> inner) {
        this.inner = inner;
    }

    public static <K, V> JMap<K, V> empty() {
        return new JMap<>(new HashMap<>());
    }

    public V getOrDefault(K key, V defaultValue) {
        return inner.getOrDefault(key, defaultValue);
    }

    public JList<K> keysWhere(Predicate<V> predicate) {
        return new JList<>(inner.entrySet()
                .stream().filter(e -> predicate.test(e.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
    }

    public JMap<K, V> minus(K key) {
        HashMap<K, V> newInner = new HashMap<>(inner);
        newInner.remove(key);
        return new JMap<>(newInner);
    }

    public JMap<K, V> with(K key, V value) {
        HashMap<K, V> newInner = new HashMap<>(inner);
        newInner.put(key, value);
        return new JMap<>(newInner);
    }

    public JList<K> keys() {
        return new JList<>(inner.keySet().stream().toList());
    }

    public JList<V> values() {
        return new JList<>(inner.values().stream().toList());
    }
}
