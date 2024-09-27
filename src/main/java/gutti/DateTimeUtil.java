package gutti;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A utility class for parsing date and time strings into {@link LocalDateTime} objects
 * using various predefined formats. This class provides methods to attempt parsing
 * with multiple date formats and returns a {@link LocalDateTime} if successful.
 */
public class DateTimeUtil {

    /**
     * Date-time formatters.
     */
    public static final DateTimeFormatter FORMAT1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter FORMAT2 = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    public static final DateTimeFormatter FORMAT3 = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
    public static final DateTimeFormatter FORMAT4 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Parses a string representing a date and time into a {@link LocalDateTime} object.
     * Attempts to parse the input string using multiple predefined date-time formats.
     *
     * @param dateTimeString The date-time string to parse.
     * @return The parsed {@link LocalDateTime} object.
     * @throws DateTimeParseException If the input string cannot be parsed into any of the supported formats.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        // Try each formatter until one succeeds
        try {
            return LocalDateTime.parse(dateTimeString, FORMAT1);
        } catch (DateTimeParseException e) {
            // Continue to next formatter
        }
        try {
            return LocalDateTime.parse(dateTimeString, FORMAT2);
        } catch (DateTimeParseException e) {
            // Continue to next formatter
        }
        try {
            return LocalDateTime.parse(dateTimeString, FORMAT3);
        } catch (DateTimeParseException e) {
            // Continue to next formatter
        }
        try {
            return LocalDateTime.parse(dateTimeString, FORMAT4);
        } catch (DateTimeParseException e) {
            // All formatters failed
            throw new DateTimeParseException("Invalid date and time format", dateTimeString, 0);
        }
    }
}
