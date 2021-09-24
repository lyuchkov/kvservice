package ru.lyuchkov.kvservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lyuchkov.kvservice.container.DataContainer;
import ru.lyuchkov.kvservice.container.StringDataContainer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SerializeServiceTest {

    @Autowired
    private SerializeService serializeService;
    @Test
    void writeStringDataContainer() {
        try {
            serializeService.writeStringDataContainer(new StringDataContainer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}