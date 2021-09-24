package ru.lyuchkov.kvservice.service;

import org.springframework.stereotype.Service;
import ru.lyuchkov.kvservice.container.StringDataContainer;
import ru.lyuchkov.kvservice.entity.Data;
import ru.lyuchkov.kvservice.utils.TtlUtil;

@Service
public class StringDataService {
    public final StringDataContainer container;

    public StringDataService(StringDataContainer container) {
        this.container = container;
    }

    public void put(long key, String value) {
        container.put(key, value);
    }

    public void put(long key, String value, long ttl) {
        container.put(key, value, ttl);
    }

    public String get(long key) {
        Data<String> data = container.getData(key);
        if(data!=null) {
            if (TtlUtil.isTimeLimitOver(data.getEndDate())) {
                container.remove(key);
                return "The lifetime value has ended";
            } else {
                return container.get(key);
            }
        } else return null;
    }

    public String remove(long key) {
        Data<String> data = container.getData(key);
        if(data!=null) {
            if (TtlUtil.isTimeLimitOver(data.getEndDate())) {
                container.remove(key);
                return null;
            } else {
                String value = container.get(key);
                container.remove(key);
                return value;
            }
        } else return null;
    }
}