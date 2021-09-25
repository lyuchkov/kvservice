package ru.lyuchkov.kvservice.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class StringTransmitService {
    private StringDataSerializeService dataSerializeService;

    public StringTransmitService(StringDataSerializeService dataSerializeService) {
        this.dataSerializeService = dataSerializeService;
    }

    public File downloadCurrentContainer(){
        try {
            dataSerializeService.writeContainer();
        }catch (Exception e){
            return null;
        }
        return new File("src/main/resources/serialized/current.container");
    }
}
