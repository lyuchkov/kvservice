package ru.lyuchkov.kvservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mvc.perform(MockMvcRequestBuilders.post("/set?key=1&value=TestValue&tll=9000"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Value has successfully set")));
        assertDoesNotThrow(()-> mvc.perform(get("/dump")).andReturn().getResponse().getContentAsString());
    }
}