package ru.lyuchkov.kvservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StringDataServiceTest {

    private final String testValue = "Test value";
    private final long customTtl = 60 * 30;
    @Autowired
    private StringDataService dataService;

    @Test
    public void dataServiceLoadsTest() {
        assertThat(dataService).isNotNull();
    }

    @Test
    public void dataServiceDataContainerLoadsTest() {
        assertNotNull(dataService.container);
    }

    @Test
    @BeforeEach
    void putTest() {
        assertDoesNotThrow(() -> dataService.put(0, testValue));
    }

    @Test
    void putWithTtlTest() {
        assertDoesNotThrow(() -> dataService.put(1, testValue, customTtl));
    }

    @Test
    void getTest() {
        assertEquals(dataService.get(0), testValue);
    }

    @Test
    void getWithWithTtlTest() {
        assertThat(dataService.get(1)).isEqualTo(testValue);
    }

    @Test
    void removeTest() {
        assertDoesNotThrow(() -> dataService.remove(0));
    }

    @Test
    void removeWithCustomTtlTest() {
        assertDoesNotThrow(() -> dataService.remove(1));
    }
}