package popi.utils;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import popi.exception.InvalidTimeFormatException;

/**
 * Utility class for parsing and formatting date and time.
 */
public class DateTimeUtils {
    private static final DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Parses a string into a LocalDateTime object.
     * @param dateTime String to be parsed.
     * @return LocalDateTime object parsed from the string.
     * @throws InvalidTimeFormatException If the string is not in the correct format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws InvalidTimeFormatException {
        try {
            return LocalDateTime.parse(dateTime, INPUT);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException("Please provide valid time format in yyyy-MM-dd HHmm");
        }
    }

    /**
     * Formats a LocalDateTime object into a string.
     * @param dateTime LocalDateTime object to be formatted.
     * @return String formatted from the LocalDateTime object.
     * @throws DateTimeException If the LocalDateTime object cannot be formatted.
     */
    public static String formatDateTime(LocalDateTime dateTime) throws DateTimeException {
        return dateTime.format(OUTPUT);
    }
}
