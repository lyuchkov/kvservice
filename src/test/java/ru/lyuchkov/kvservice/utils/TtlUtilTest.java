package ru.lyuchkov.kvservice.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lyuchkov.kvservice.container.DataContainer;
import ru.lyuchkov.kvservice.entity.Data;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TtlUtilTest {
    @Autowired
    DataContainer<String> dataContainer;
    @Autowired
    TtlUtil ttlUtil;
    @Test
    void iteratorTest() throws InterruptedException {
        dataContainer.put(0, "value", 500);
        dataContainer.put(1, "value600", 600);
        dataContainer.put(2, "value10", 1 );
        Thread.sleep(1000);
        ttlUtil.removeAllLifelessValues();
        int count =0;
        Iterator<Long> secondLongIterator = dataContainer.keyIterator();
        while (secondLongIterator.hasNext()){
            count++;
            secondLongIterator.next();
        }
        assertEquals(2, count);
    }
}