package ru.lyuchkov.kvservice.service;

import org.springframework.stereotype.Component;
import ru.lyuchkov.kvservice.container.StringDataContainer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Component
public class SerializeService {
    public void writeStringDataContainer(StringDataContainer container) throws IOException {
        File file = new File("current.container");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(container);
        oos.close();
    }
}
