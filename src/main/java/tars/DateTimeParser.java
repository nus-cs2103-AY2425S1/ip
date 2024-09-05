package tars;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing and formatting date-time strings.
 *
 * <p>The DateTimeParser class provides methods to parse date-time strings into
 * {@link LocalDateTime} objects and to format {@link LocalDateTime} objects into strings.
 * It supports multiple date-time formats, making it flexible for different input formats.
 */
public class DateTimeParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
    private static final DateTimeFormatter STORED_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

    /**
     * Parses a date-time string into a {@link LocalDateTime} object.
     * This method first attempts to parse the string using the format "yyyy-MM-dd HHmm".
     * If that fails, it tries to parse the string using the format "dd MMM yyyy, HH:mm".
     *
     * @param dateTimeString The date-time string to be parsed.
     * @return The parsed {@link LocalDateTime} object.
     * @throws DateTimeParseException if the string cannot be parsed.
     */
    public static LocalDateTime parse(String dateTimeString) throws DateTimeParseException {
        try {
            System.out.println("Attempting to parse date: " + dateTimeString);
            LocalDateTime parsedDate = LocalDateTime.parse(dateTimeString.trim(), DATE_TIME_FORMATTER);
            assert parsedDate != null : "Parsed date should not be null.";
            return parsedDate;
        } catch (DateTimeParseException e) {
            // If parsing with "yyyy-MM-dd HHmm" fails, try parsing with "dd MMM yyyy, HH:mm"
            LocalDateTime parsedDate = LocalDateTime.parse(dateTimeString.trim(), STORED_FORMATTER);
            assert parsedDate != null : "Parsed date with stored format should not be null.";
            return parsedDate;
        }
    }

    /**
     * Formats a {@link LocalDateTime} object into a string using the format "dd MMM yyyy, HH:mm".
     *
     * @param dateTime The {@link LocalDateTime} object to be formatted.
     * @return The formatted date-time string.
     */
    public static String format(LocalDateTime dateTime) {
        String formattedDate = dateTime.format(OUTPUT_FORMATTER);
        assert formattedDate != null && !formattedDate.isEmpty() : "Formatted date should not be null or empty.";
        return formattedDate;

    }
}


