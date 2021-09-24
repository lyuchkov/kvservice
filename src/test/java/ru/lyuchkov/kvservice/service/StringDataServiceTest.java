package ru.lyuchkov.kvservice.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.lyuchkov.kvservice.container.StringDataContainer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class StringDataServiceTest {
    private static StringDataService dataService;

    private final String testValue = "Test value";
    private final long customTtl = 60 * 30;

    @BeforeAll
    static void init() {
        dataService = new StringDataService(new StringDataContainer());
    }

    @Test
    public void dataServiceLoadsTest() {
        assertThat(dataService).isNotNull();
    }

    @Test
    public void dataServiceDataContainerLoadsTest() {
        assertThat(dataService.container).isNotNull();
    }

    @Test
    void putTest() {
        assertDoesNotThrow(() -> dataService.put(0, testValue));
    }

    @Test
    void putWithTtlTest() {
        assertDoesNotThrow(() -> dataService.put(1, testValue, customTtl));
    }

    @Test
    void getTest() {
        assertThat(dataService.get(0)).isEqualTo(testValue);
    }

    @Test
    void getWithTtlTest() {
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