package org.example;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;

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
        VALUES.put("REJECT", "Отклонен");
    }
    public static Status ofCode(String code) {
        Status codeName = new Status();
        codeName.setCode(code);
        codeName.setName(VALUES.get(code));
        return codeName;
    }

    public static Status ofName(String name) {
        Status codeName = new Status();
        codeName.setName(name);
        if (VALUES.get("NEW").equals(name)){
            codeName.setCode("NEW");
        }
        if (VALUES.get("IN_PROCESS").equals(name)){
            codeName.setCode("IN_PROCESS");
        }
        return codeName;
    }
}
