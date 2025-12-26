package com.example.demo.util;

import java.time.LocalDate;

public class DateUtil {

    private DateUtil() {}

    public static LocalDate today() {
        return LocalDate.now();
    }
}
