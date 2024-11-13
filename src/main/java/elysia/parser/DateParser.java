package elysia.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import elysia.exception.InvalidDateFormatException;

/**
 * Parses the string input into LocalDate object.
 **/
public class DateParser {

    /**
     * Parses the string input into LocalDate object.
     *
     * @param input
     * @return
     */
    public static LocalDate parseDate(String input) throws InvalidDateFormatException {
        LocalDate parsedDate = null;

        //get dates for next week
        Map<String, LocalDate> map = buildMap();

        parsedDate = map.get(input.toLowerCase());

        if (parsedDate != null) {
            return parsedDate;
        }

        DateTimeFormatter[] formatters = getDateTimeFormatters();

        for (DateTimeFormatter formatter : formatters) {
            try {
                parsedDate = LocalDate.parse(input, formatter);
                break; // Exit loop if parsing is successful
            } catch (DateTimeParseException e) {
                // Continue to the next format if parsing fails
                // hence the catch block here is leaved empty
            }

            try {
                parsedDate = LocalDate.parse(input + " " + LocalDate.now().getYear(), formatter);
                break;
            } catch (DateTimeParseException e) {
                // throws the exception and return null if parsing fails
                // the exception will be handled at Elysia
            }
        }

        checkValidDateInput(parsedDate);

        return parsedDate;
    }

    /**
     * @return the date formatters used
     */
    private static DateTimeFormatter[] getDateTimeFormatters() {
        DateTimeFormatter[] formatters = {
                //handle suffix (st, rd, etc)
                DateTimeFormatter.ofPattern("d['st']['nd']['rd']['th'] MMM yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/M/d"),
                DateTimeFormatter.ofPattern("yyyy-M-d"),
                DateTimeFormatter.ofPattern("d-M-yyyy")
        };
        return formatters;
    }

    /**
     * Builds a map that map the next subsequent dates for Mon, Tue...Sun.
     **/
    public static Map<String, LocalDate> buildMap() {
        Map<String, LocalDate> map = new HashMap<>();
        LocalDate today = LocalDate.now();

        for (int i = 1; i < 8; i++) {
            LocalDate date = today.plusDays(i);
            String dayOfWeek = date.getDayOfWeek().name().toLowerCase();
            map.put(dayOfWeek, date);
            map.put(dayOfWeek.substring(0, 3), date);
        }

        return map;
    }

    private static void checkValidDateInput(LocalDate date) throws InvalidDateFormatException {
        if (date == null) {
            throw new InvalidDateFormatException();
        }
    }

}
