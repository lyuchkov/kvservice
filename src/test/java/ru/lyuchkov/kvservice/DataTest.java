package ru.lyuchkov.kvservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lyuchkov.kvservice.entity.Data;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DataTest {
    public final long customTtl = 60 * 5;

    @Test
    public void DataConstructorWithTtlValueTest() {
        Data<String> data = new Data<>("value", customTtl);

        assertEquals("value", data.getValue());
    }

    @Test
    public void DataConstructorValueTest() {
        Data<String> data = new Data<>("value");

        assertEquals("value", data.getValue());
    }
}
