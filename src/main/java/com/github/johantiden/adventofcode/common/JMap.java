package com.github.johantiden.adventofcode.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class JMap<K, V> {

    private final Map<K, V> inner;

    public JMap(Map<K, V> inner) {
        this.inner = inner;
    }

    public static <K, V> JMap<K, V> empty() {
        return new JMap<>(new HashMap<>());
    }

    public static <K, V> JMap<K, V> toMap(JList<Pair<K, V>> listOfPairs) {
        Map<K, V> inner = new HashMap<>();
        listOfPairs.forEach(pair -> inner.put(pair.left(), pair.right()));
        return new JMap<>(inner);
    }

    public V getOrDefault(K key, V defaultValue) {
        return inner.getOrDefault(key, defaultValue);
    }

    public V getOrThrow(K key) {
        if (!inner.containsKey(key)) {
            throw new IllegalStateException("Could not find key '"+key+"'");
        }
        return inner.get(key);
    }

    public V getOrThrow(Predicate<K> predicate) {
        K oneKey = JList.copyOf(inner.keySet())
                .findOne(predicate);
        return getOrThrow(oneKey);
    }

    public Optional<K> tryGetKey(Predicate<K> predicate) {
        List<K> matchingKeys = inner
                .keySet().stream()
                .filter(predicate)
                .toList();
        if (matchingKeys.size() == 1) {
            return Optional.of(matchingKeys.get(0));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Pair<K, V>> tryGetEntryWhereValue(Predicate<V> predicate) {
        List<Map.Entry<K, V>> matchingEntries = inner.entrySet()
                .stream()
                .filter(entry -> predicate.test(entry.getValue()))
                .toList();
        if (matchingEntries.size() == 1) {
            return Optional.of(new Pair<>(matchingEntries.get(0).getKey(), matchingEntries.get(0).getValue()));
        } else {
            return Optional.empty();
        }
    }

    public JList<K> keysWhereValue(Predicate<V> predicate) {
        return new JList<>(inner.entrySet()
                .stream().filter(e -> predicate.test(e.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
    }

    public JList<K> keysWhereKey(Predicate<K> predicate) {
        return new JList<>(inner.keySet()
                .stream()
                .filter(predicate)
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

    public JMap<K, V> with(K key, UnaryOperator<V> valueMapper) {
        HashMap<K, V> newInner = new HashMap<>(inner);
        V existing = inner.get(key);
        if (existing == null) {
            throw new IllegalStateException("You cannot use a mapper only, when the JMap is missing the key! Please populate the JMap with all possible values or use the overload that takes a defaultValue");
        }
        V newValue = valueMapper.apply(existing);
        newInner.put(key, newValue);
        return new JMap<>(newInner);
    }

    public JMap<K, V> with(K key, V defaultValue, UnaryOperator<V> valueMapper) {
        HashMap<K, V> newInner = new HashMap<>(inner);
        V existing = inner.get(key);
        if (existing == null) {
            newInner.put(key, valueMapper.apply(defaultValue));
        } else {
            newInner.put(key, valueMapper.apply(existing));
        }
        return new JMap<>(newInner);
    }

    public JMap<K, V> mapValues(UnaryOperator<V> valueMapper) {
        HashMap<K, V> newInner = new HashMap<>();
        for (Map.Entry<K, V> entry : inner.entrySet()) {
            newInner.put(entry.getKey(), valueMapper.apply(entry.getValue()));
        }
        return new JMap<>(newInner);
    }

    public JList<K> keys() {
        return new JList<>(inner.keySet().stream().toList());
    }

    public JList<V> values() {
        return new JList<>(inner.values().stream().toList());
    }

    public boolean isEmpty() {
        return keys().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JMap<?, ?> jMap = (JMap<?, ?>) o;
        return inner.equals(jMap.inner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inner);
    }

    @Override
    public String toString() {
        return inner.toString();
    }

    public JList<Pair<K, V>> entries() {
        return new JList<>(
                inner.entrySet().stream().map(e -> new Pair<>(e.getKey(), e.getValue())).toList()
        );
    }
}
