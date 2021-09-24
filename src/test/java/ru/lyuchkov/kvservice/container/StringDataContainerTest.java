package ru.lyuchkov.kvservice.container;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StringDataContainerTest {
    private final String testValue = "TestValue";
    private final long ttl = 60;
    @Autowired
    private DataContainer<String> dataContainer;

    @Test
    @BeforeEach
    void putWithDefaultTtlTest() {
        assertDoesNotThrow(() -> dataContainer.put(0, testValue));
    }

    @Test
    @BeforeEach
    void putWithCustomTtlTest() {
        assertDoesNotThrow(() -> dataContainer.put(1, testValue + "  custom ttl"));
    }


    @Test
    void getWithCustomTtlTest() {
        assertEquals(dataContainer.get(1), testValue+ "  custom ttl");
    }

    @Test
    void getTest() {
        assertEquals(dataContainer.get(0), testValue );
    }

    @Test
    void removeTest() {
        assertDoesNotThrow(() -> dataContainer.remove(0));
    }

    @Test
    void getDataTest() {
        dataContainer.put(1, testValue);
        assertEquals(dataContainer.getData(1).getValue(), testValue);
    }
}