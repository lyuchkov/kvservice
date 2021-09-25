package ru.lyuchkov.kvservice.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StringTransmitService {
    private final StringDataSerializeService dataSerializeService;

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

    public HttpHeaders prepareHeaderForFileReturn(String name, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/container");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        return headers;
    }


    public Resource getFileAsResource(File serializedContainer) throws FileNotFoundException {
        return loadAsResource(serializedContainer.getAbsolutePath());
    }

    private Resource loadAsResource(String absolutePath) throws FileNotFoundException {
        try {
            Path file = Paths.get(absolutePath);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException();
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException();
        }

    }
    public void uploadDataContainer(){
         dataSerializeService.readContainer();
    }
}
