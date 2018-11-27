package ru.javawebinar.topjava.util;

import org.slf4j.Logger;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.slf4j.LoggerFactory.getLogger;

public class LocalDateFormatter implements Formatter<LocalDate> {
    private static final Logger log = getLogger(LocalDateFormatter.class);

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        log.info("I'm working");
        return LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return object.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
