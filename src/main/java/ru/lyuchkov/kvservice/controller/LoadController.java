package ru.lyuchkov.kvservice.controller;

import org.springframework.web.bind.annotation.*;
import ru.lyuchkov.kvservice.service.StringDataService;

@RestController
public class LoadController {
    public final StringDataService dataService;

    public LoadController(StringDataService dataService) {
        this.dataService = dataService;
    }

    //todo Сохраняет текущее состояние хранилища и возвращает его в виде загружаемого файла
    // + проверка каждого и очистка от "мертвых значений"

    //todo Загружает состояние хранилища из файла, созданного операцией dump
    // + проверка каждого и очистка от "мертвых значений"
}
