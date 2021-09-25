package ru.lyuchkov.kvservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lyuchkov.kvservice.container.DataContainer;
import ru.lyuchkov.kvservice.entity.Data;
import ru.lyuchkov.kvservice.utils.SerializeUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class StringDataSerializeServiceTest {

    @Autowired
    private StringDataSerializeService serializeService;

    @Autowired
    private DataContainer<String> dataContainer;
    @Test
    @BeforeEach
    void writeStringDataContainer() {
        dataContainer.put(0, "test value" ,100);
        serializeService.writeContainer();
    }
    @Test
    void readStringDataContainer() {
        serializeService.readContainer();
        assertEquals("test value", dataContainer.getData(0).getValue());
    }
    /*
    @Test
    void readStringDataContainer() {
        try {
            DataContainer<String> container = serializeUtil.readStringDataContainer();
            assert container != null;
            assertEquals(container.get(0), "test value");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     */
}