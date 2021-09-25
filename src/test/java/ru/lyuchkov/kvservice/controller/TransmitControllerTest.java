package ru.lyuchkov.kvservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class TransmitControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TransmitController TransmitController;

    @Test
    @BeforeEach
    public void initTest() {
        assertNotNull(TransmitController);
    }

    @Test
    void download() throws Exception {
        System.out.println(mvc.perform(get("/pump")).andReturn().getResponse().getContentType());
    }
}