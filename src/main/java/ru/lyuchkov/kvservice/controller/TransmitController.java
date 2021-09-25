package ru.lyuchkov.kvservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lyuchkov.kvservice.service.StringTransmitService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class TransmitController {
    public final StringTransmitService transmitService;

    public TransmitController(StringTransmitService transmitService) {
        this.transmitService = transmitService;
    }

    @GetMapping("/pump")
    public void download(HttpServletResponse response) {
        File serializedContainer = transmitService.downloadCurrentContainer();
        response.setContentType("application/container");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + serializedContainer.getName();

        response.setHeader(headerKey, headerValue);

        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(serializedContainer));
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[8192];
            int bytesRead = -1;
            while((bytesRead = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }






    }
    //todo Загружает состояние хранилища из файла, созданного операцией dump
    // + проверка каждого и очистка от "мертвых значений"
}
