package morgana.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import morgana.exceptions.MorganaException;

/**
 * Utility class that provides methods to handle date and time parsing and formatting.
 */
public class DateTimeUtil {
    public static final String COMPACT_PATTERN = "yyyy-MM-dd HHmm";
    public static final String VERBOSE_PATTERN = "MMM dd yyyy, h:mm a";

    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT =
            "Invalid date/time format. Please use '%s'.".formatted(COMPACT_PATTERN);

    public static final DateTimeFormatter COMPACT_FORMATTER = DateTimeFormatter.ofPattern(COMPACT_PATTERN);
    public static final DateTimeFormatter VERBOSE_FORMATTER = DateTimeFormatter.ofPattern(VERBOSE_PATTERN);

    /**
     * Parses a date/time string into a {@link LocalDateTime} object.
     *
     * @param dateTime The input string containing the date and time in the format 'yyyy-MM-dd HHmm'.
     * @return The parsed {@code LocalDateTime} object.
     * @throws MorganaException If the input string is in an invalid format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws MorganaException {
        try {
            return LocalDateTime.parse(dateTime, COMPACT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MorganaException(MESSAGE_INVALID_DATE_TIME_FORMAT);
        }
    }
}
