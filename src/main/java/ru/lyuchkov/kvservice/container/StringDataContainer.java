package ru.lyuchkov.kvservice.container;


import org.springframework.stereotype.Component;
import ru.lyuchkov.kvservice.entity.Data;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StringDataContainer implements Serializable {
    private final ConcurrentHashMap<Long, Data<String>> hashMap;

    public StringDataContainer() {
        this.hashMap = new ConcurrentHashMap<>();
    }

    public void put(long key, String value) {
        if (hashMap.containsKey(key)) {
            hashMap.get(key).setValue(value);
        } else {
            hashMap.put(key, new Data<>(value));
        }
    }

    public void put(long key, String value, long ttl) {
        if (hashMap.containsKey(key)) {
            hashMap.get(key).setValue(value, ttl);
        } else {
            hashMap.put(key, new Data<>(value, ttl));
        }
    }

    public String get(long key) {
        return hashMap.get(key).getValue();
    }

    public void remove(long key) {
        hashMap.remove(key);
    }
    public Data<String> getData(long key){
        return hashMap.getOrDefault(key, null);
    }
}
