package com.prog2.registrofazenda.utils;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    private DateUtils() {
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date convertDateStringToDateObj(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateString) {
        return dateString;
    }
}

