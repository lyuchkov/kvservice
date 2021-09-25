package ru.lyuchkov.kvservice.utils;

import org.springframework.stereotype.Component;
import ru.lyuchkov.kvservice.container.DataContainer;

import java.io.*;

@Component
public class SerializeUtil<V> {
    public void writeStringDataContainer(DataContainer<V> container) throws IOException {
        File file = new File("src/main/resources/serialized/current.container");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(container);
        oos.close();
    }
    @SuppressWarnings({"unchecked"})
    public DataContainer<V> readStringDataContainer() throws IOException, ClassNotFoundException {
        File file = new File("src/main/resources/serialized/current.container");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        return o instanceof DataContainer ? (DataContainer<V>) o : null;
    }
}
