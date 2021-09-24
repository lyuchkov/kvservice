package ru.lyuchkov.kvservice.controller;

import org.springframework.web.bind.annotation.*;
import ru.lyuchkov.kvservice.service.StringDataService;

@RestController
public class AccessController {
    public final StringDataService dataService;

    public AccessController(StringDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/get")
    public String getValue(@RequestParam(value = "key") String strKey) {
        long key;
        try {
            key = Long.parseLong(strKey);
        } catch (NumberFormatException e) {
            return "Incorrect number format.";
        }
        String s = dataService.get(key);
        if (s != null) return s;
        else return "The key hasn't found";
    }

    @PostMapping("/set")
    public String setValue(@RequestParam(value = "key") String strKey,
                           @RequestParam(value = "value") String value,
                           @RequestParam(value = "ttl", required = false) String ttl) {
        long key;
        try {
            key = Long.parseLong(strKey);
        } catch (NumberFormatException e) {
            return "Incorrect number format.";
        }
        if (ttl != null) {
            dataService.put(key, value, Long.parseLong(ttl));
        } else {
            dataService.put(key, value);
        }
        return "Value has successfully set";

    }

    @DeleteMapping("/remove")
    public String remove(@RequestParam(value = "key") String strKey) {
        long key;
        try {
            key = Long.parseLong(strKey);
        } catch (NumberFormatException e) {
            return "Incorrect number format.";
        }
        String s = dataService.remove(key);
        if (s != null) return s;
        else return "The key hasn't found";
    }
}
