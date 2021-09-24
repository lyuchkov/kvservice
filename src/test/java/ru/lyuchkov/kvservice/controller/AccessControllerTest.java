package ru.lyuchkov.kvservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccessControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccessController accessController;

    @Test
    @BeforeEach
    public void initTest() {
        assertNotNull(accessController);
    }

    @Test
    @BeforeEach
    public void setValueWithCorrectKey() throws Exception {
        mvc.perform(post("/set?key=0&value=TestValue"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Value has successfully set")));

    }

    @Test
    public void setValueWithIncorrectKey() throws Exception {
        mvc.perform(post("/set?key=abc&value=TestValue"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Incorrect number format")));
    }

    @Test
    public void getValueWithCorrectKey() throws Exception {
        mvc.perform(get("/get?key=0"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TestValue")));
    }

    @Test
    public void getValueWithIncorrectKey() throws Exception {
        mvc.perform(get("/get?key=abc"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Incorrect number format")));
    }

    @Test
    public void getWithNotDefinedKey() throws Exception {
        mvc.perform(get("/get?key=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("The key hasn't found")));
    }

    @Test
    public void customTtlOverTest() throws Exception {
        mvc.perform(post("/set?key=1&value=TestValue&ttl=10"));
        Thread.sleep(10 * 1000);
        mvc.perform(get("/get?key=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("The lifetime value has ended")));
    }
    @Test
    public void defaultTtlOverTest() throws Exception {
        mvc.perform(post("/set?key=1&value=TestValue"));
        Thread.sleep(60 * 1000);
        mvc.perform(get("/get?key=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("The lifetime value has ended")));
    }
    @Test
    public void removeWithCorrectKey() throws Exception {
        mvc.perform(post("/set?key=2&value=TestValue&ttl=10"));
        mvc.perform(get("/get?key=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TestValue")));
    }

    @Test
    public void removeWithIncorrectKey() throws Exception {
        mvc.perform(delete("/remove?key=abc"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Incorrect number format")));
    }

    @Test
    public void removeWithNotDefinedKey() throws Exception {
        mvc.perform(delete("/remove?key=3"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("The key hasn't found")));
    }
}