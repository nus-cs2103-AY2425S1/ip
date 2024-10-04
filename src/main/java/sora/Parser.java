package sora;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parser parses the User's input.
 * It deals with making sense of the user command.
 * It identifies mainCommand and additional details in the User's input.
 */
public class Parser {
    /**
     * Returns an ArrayList that parses the User's command.
     * Identifies mainCommand and additional details in the User's input.
     * This Method uses Streams.
     *
     * @param command User's command / input.
     * @return ArrayList containing parsed command.
     */
    public static ArrayList<String> parse(String command) {
        ArrayList<String> parsedResult = new ArrayList<>();
        String[] parseSpaces = command.split(" ", 2);
        parsedResult.add(parseSpaces[0]);
        if (parseSpaces.length > 1) {
            String[] parseSlashes = parseSpaces[1].split(" /", 3);
            parsedResult.addAll(Arrays.stream(parseSlashes).toList());
        }
        return parsedResult;
    }

    /**
     * Returns LocalDateTime DAY/MONTH/YEAR HOUR MINUTE of a String dateTime.
     * If date is invalid, null is returned.
     *
     * @param dateTime Date & Time in String Format.
     * @return LocalDateTime DAY/MONTH/YEAR HOUR MINUTE.
     */
    public static LocalDateTime parseDate(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
