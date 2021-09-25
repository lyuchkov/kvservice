package ru.lyuchkov.kvservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lyuchkov.kvservice.container.DataContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StringDataSerializeServiceTest {

    @Autowired
    private StringDataSerializeService serializeService;

    @Autowired
    private DataContainer<String> dataContainer;

    @Test
    @BeforeEach
    void writeStringDataContainer() {
        dataContainer.put(0, "test value", 100);
        serializeService.writeContainer();
    }

    @Test
    void readStringDataContainer() {
        serializeService.readContainer();
        assertEquals("test value", dataContainer.getData(0).getValue());
    }
}