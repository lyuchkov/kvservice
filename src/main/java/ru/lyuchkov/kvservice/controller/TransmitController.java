package ru.lyuchkov.kvservice.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.lyuchkov.kvservice.service.StringTransmitService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class TransmitController {
    public final StringTransmitService transmitService;

    public TransmitController(StringTransmitService transmitService) {
        this.transmitService = transmitService;
    }

    @GetMapping("/dump")
    public ResponseEntity<Resource> download(HttpServletResponse response) throws FileNotFoundException {
        File serializedContainer = transmitService.downloadCurrentContainer();
        Resource file = transmitService.getFileAsResource(serializedContainer);
        HttpHeaders headers = transmitService.prepareHeaderForFileReturn(serializedContainer.getName(), response);
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    @PostMapping("/load")
    public ResponseEntity<String> load(@RequestParam("file") MultipartFile multipartFile) {
        File file = new File("src/main/resources/serialized/current.container");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            transmitService.uploadDataContainer();
            return new ResponseEntity<>("The file uploaded successfully", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("The file was not uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(FileNotFoundException.class)
    public String handleFileNotFoundException() {
        return "File not found";
    }

    @ExceptionHandler(IOException.class)
    public String handleIOException() {
        return "IO exception";
    }
}
