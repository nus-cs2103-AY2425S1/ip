package silverwolf.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import silverwolf.exception.SilverWolfException;

/**
 * The DateTimeParser class provides utilities for parsing date/time strings into LocalDateTime objects
 * and formatting LocalDateTime objects into strings. It supports a variety of date/time formats to accommodate
 * different input styles.
 */
public class DateTimeParser {

    // List of possible date formats
    private static final List<DateTimeFormatter> formatters = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy ha"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy ha"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd ha"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("HHmm"),
            DateTimeFormatter.ofPattern("h:mma"),
            DateTimeFormatter.ofPattern("ha"),
            DateTimeFormatter.ofPattern("HHmm")
    );

    // Formatter for output string format
    private static final DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Parses a date/time string into a LocalDateTime object using the predefined list of DateTimeFormatters.
     * The method iterates through the list of formatters and attempts to parse the string with each one.
     * If a match is found, the corresponding LocalDateTime object is returned. If no match is found,
     * a SilverWolfException is thrown.
     *
     * @param dateTimeString The date/time string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws SilverWolfException If none of the date formats match the input string.
     */
    public LocalDateTime parseDateTime(String dateTimeString) throws SilverWolfException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                // Try the next pattern
            }
        }
        throw new SilverWolfException("Hey! None of the date formats matched. Please check your input.");
    }

    /**
     * Formats a LocalDateTime object into a string using the predefined output format.
     * The format used is "MMM dd yyyy, h:mm a", which provides a readable date and time format.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return The formatted date/time string.
     */
    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(dateTimeOutputFormatter);
    }
}
