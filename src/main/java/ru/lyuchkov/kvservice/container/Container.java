package ru.lyuchkov.kvservice.container;

import java.io.Serializable;
import java.util.Iterator;

public interface Container<V> extends Serializable {
    void put(long key, V value);

    void put(long key, V value, long ttl);

    V get(long key);

    void remove(long key);

    Iterator<Long> keyIterator();
}
