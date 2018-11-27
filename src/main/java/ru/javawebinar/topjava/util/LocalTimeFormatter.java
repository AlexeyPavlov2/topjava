package ru.javawebinar.topjava.util;

import org.slf4j.Logger;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.slf4j.LoggerFactory.getLogger;

public class LocalTimeFormatter implements Formatter<LocalTime> {
    private static final Logger log = getLogger(LocalTimeFormatter.class);
    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException {
        log.info("I'm working");
        return LocalTime.parse(text, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        return object.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
