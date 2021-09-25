package ru.lyuchkov.kvservice.container;


import org.springframework.stereotype.Component;
import ru.lyuchkov.kvservice.entity.Data;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataContainer<V> implements Container<V> {
    private final ConcurrentHashMap<Long, Data<V>> hashMap;

    public DataContainer() {
        this.hashMap = new ConcurrentHashMap<>();
    }

    public void put(long key, V value) {
        if (hashMap.containsKey(key)) {
            hashMap.get(key).setValue(value);
        } else {
            hashMap.put(key, new Data<>(value));
        }
    }

    public void put(long key, V value, long ttl) {
        if (hashMap.containsKey(key)) {
            hashMap.get(key).setValue(value, ttl);
        } else {
            hashMap.put(key, new Data<>(value, ttl));
        }
    }

    public V get(long key) {
        return hashMap.get(key).getValue();
    }

    public void remove(long key) {
        hashMap.remove(key);
    }

    public Data<V> getData(long key) {
        return hashMap.getOrDefault(key, null);
    }

    public Iterator<Long> keyIterator() {
        return hashMap.keySet().stream().iterator();
    }
}