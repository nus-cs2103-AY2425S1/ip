package elysia.parser;

import elysia.exception.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses the string input into LocalDate object.
 **/
public class DateParser {

    /**
     * Parses the string input into LocalDate object.
     **/
    public static LocalDate parseDate(String input) {
        LocalDate parsedDate = null;

        //get dates for next week
        Map<String, LocalDate> map = buildMap();

        parsedDate = map.get(input.toLowerCase());

        if (parsedDate != null) {
            return parsedDate;
        }

        DateTimeFormatter[] formatters = {
                //handle suffix (st, rd, etc)
                DateTimeFormatter.ofPattern("d['st']['nd']['rd']['th'] MMM yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/M/d"),
                DateTimeFormatter.ofPattern("yyyy-M-d"),
                DateTimeFormatter.ofPattern("d-M-yyyy")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                parsedDate = LocalDate.parse(input, formatter);
                break; // Exit loop if parsing is successful
            } catch (DateTimeParseException e) {
                // Continue to the next format if parsing fails
            }
        }

        if (parsedDate == null) {
            for (DateTimeFormatter formatter : formatters) {
                try {
                    parsedDate = LocalDate.parse(input + " " + LocalDate.now().getYear(), formatter);
                    break; // Exit loop if parsing is successful
                } catch (DateTimeParseException e) {
                    // Continue to the next format if parsing fails
                }
            }
        }

        return parsedDate;
    }

    /**
     * Builds a map that map the subsequent dates for Mon, Tues...Sun.
     **/
    static Map<String, LocalDate> buildMap() {
        Map<String, LocalDate> map = new HashMap<>();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            String dayOfWeek = date.getDayOfWeek().name().toLowerCase();
            map.put(dayOfWeek, date);
            map.put(dayOfWeek.substring(0, 3), date);
        }

        return map;
    }
}