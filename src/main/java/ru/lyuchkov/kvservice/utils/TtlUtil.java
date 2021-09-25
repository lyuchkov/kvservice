package ru.lyuchkov.kvservice.utils;

import org.springframework.stereotype.Component;
import ru.lyuchkov.kvservice.container.DataContainer;

import java.util.Date;
import java.util.Iterator;

@Component
public class TtlUtil {
    private final DataContainer<String> dataContainer;

    public TtlUtil(DataContainer<String> dataContainer) {
        this.dataContainer = dataContainer;
    }

    public boolean isTimeLimitOver(Date endDate) {
        return new Date().after(endDate);
    }

    public void removeAllLifelessValues() {
        Iterator<Long> longIterator = dataContainer.keyIterator();
        while (longIterator.hasNext()) {
            long key = longIterator.next();
            if (isTimeLimitOver(dataContainer.getData(key).getEndDate())) {
                dataContainer.remove(key);
            }
        }
    }
}
