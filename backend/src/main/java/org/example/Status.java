package org.example;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.NoSuchElementException;

@Data
public class Status {
    private String code;
    private String name;
    private static final HashMap<String, String> VALUES;
    static {
        VALUES=new HashMap<>();
        VALUES.put("NEW","Новый");
        VALUES.put("IN_PROCESS", "В обработке");
        VALUES.put("ACCEPTED","Принят");
        VALUES.put("DECLINED", "Отклонен");
    }
    public static Status ofCode(String code) {
        Status codeName = new Status();
        codeName.setCode(code);
        if (!VALUES.containsKey(code)) {
            throw new NoSuchElementException("Такого ключа нет!");
        }
        codeName.setName(VALUES.get(code));
        return codeName;
    }

    public static Status ofName(String name) {
        Status codeName = new Status();
        codeName.setName(name);
        if (!VALUES.containsValue(name)){
            throw new NoSuchElementException("Статуса с таким именем нет!");
        }
        if (VALUES.get("NEW").equals(name)){
            codeName.setCode("NEW");
        }
        if (VALUES.get("IN_PROCESS").equals(name)){
            codeName.setCode("IN_PROCESS");
        }
        if (VALUES.get("ACCEPTED").equals(name)){
            codeName.setCode("Принят");
        }
        if (VALUES.get("DECLINED").equals(name)){
            codeName.setCode("Отклонен");
        }
        return codeName;
    }
}
