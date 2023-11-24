package org.example;

import lombok.*;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Класс статуса документа
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Status {
    /**
     * Код статуса для использования в логике программы
     */
    private String code;
    /**
     * Имя статуса для записи в базу данных
     */
    private String name;
    /**
     * Возможные значения статуса
     */
    private static final HashMap<String, String> VALUES;

    static {
        VALUES = new HashMap<>();
        VALUES.put("NEW", "Новый");
        VALUES.put("IN_PROCESS", "В обработке");
        VALUES.put("ACCEPTED", "Принят");
        VALUES.put("DECLINED", "Отклонен");
    }

    public Status(String code) {
        this.code = code;
        if (!VALUES.containsKey(code)) {
            throw new NoSuchElementException("Такого кода статуса нет!");
        }
        this.name = VALUES.get(code);
    }

    /**
     * Получение объекта статуса по имени
     *
     * @param name имя статуса
     * @return объект статуса
     */
    public static Status ofName(String name) {
        Status codeName = new Status();
        codeName.setName(name);
        if (!VALUES.containsValue(name)) {
            throw new NoSuchElementException("Статуса с таким именем нет!");
        }
        if (VALUES.get("NEW").equals(name)) {
            codeName.setCode("NEW");
        }
        if (VALUES.get("IN_PROCESS").equals(name)) {
            codeName.setCode("IN_PROCESS");
        }
        if (VALUES.get("ACCEPTED").equals(name)) {
            codeName.setCode("Принят");
        }
        if (VALUES.get("DECLINED").equals(name)) {
            codeName.setCode("Отклонен");
        }
        return codeName;
    }
}
