package ru.lyuchkov.kvservice.service;

import org.springframework.stereotype.Service;
import ru.lyuchkov.kvservice.container.DataContainer;
import ru.lyuchkov.kvservice.utils.SerializeUtil;
import ru.lyuchkov.kvservice.utils.TtlUtil;

import java.io.IOException;

@Service
public class StringDataSerializeService {
    private final SerializeUtil<String> serializeUtil;
    private final TtlUtil ttlUtil;
    private final DataContainer<String> dataContainer;
    private final StringDataService dataService;

    public StringDataSerializeService(SerializeUtil<String> serializeUtil, TtlUtil ttlUtil, DataContainer<String> dataContainer,  StringDataService dataService) {
        this.serializeUtil = serializeUtil;
        this.ttlUtil = ttlUtil;
        this.dataContainer = dataContainer;
        this.dataService = dataService;
    }

    public void writeContainer() {
        try {
            ttlUtil.removeAllLifelessValues();
            serializeUtil.writeStringDataContainer(dataContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readContainer() {
        try {
            DataContainer<String> container = serializeUtil.readStringDataContainer();
            if (container != null) {
                dataService.setContainer(container);
                ttlUtil.removeAllLifelessValues();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
