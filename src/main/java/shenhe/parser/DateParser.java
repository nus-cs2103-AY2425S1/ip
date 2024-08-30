package shenhe.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing and formatting date and time strings.
 * <p>
 * The {@code DateParser} class provides methods to parse date and time strings into {@code LocalDateTime} objects
 * and to format {@code LocalDateTime} objects into specific string formats. It supports two formats:
 * one for user input and another for file storage.
 * </p>
 */
public class DateParser {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    /**
     * Parses a date and time string in the input format ("dd/MM/yyyy HHmm") into a {@code LocalDateTime} object.
     *
     * @param dateTimeStr The date and time string to parse, expected in "dd/MM/yyyy HHmm" format.
     * @return A {@code LocalDateTime} object representing the parsed date and time, or {@code null} if the input
     *         string does not match the expected format.
     */
    public static LocalDateTime parse(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format.");
            return null;
        }
    }

    /**
     * Parses a date and time string from the file format ("MM-dd-yyyy HH:mm") into a {@code LocalDateTime} object.
     *
     * @param dateTimeStr The date and time string to parse, expected in "MM-dd-yyyy HH:mm" format.
     * @return A {@code LocalDateTime} object representing the parsed date and time, or {@code null} if the input
     *         string does not match the expected format.
     */
    public static LocalDateTime parseFile(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, OUTPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format.");
            return null;
        }
    }

    /**
     * Formats a {@code LocalDateTime} object into a string in the output format ("MM-dd-yyyy HH:mm").
     *
     * @param dateTime The {@code LocalDateTime} object to format.
     * @return A formatted date and time string in "MM-dd-yyyy HH:mm" format.
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMATTER);
    }
}
