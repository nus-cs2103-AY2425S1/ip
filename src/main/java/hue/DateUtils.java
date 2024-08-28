package hue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * A utility class for date and time operations.
 * Provides methods to parse date and time strings into {@code LocalDateTime} objects.
 */
public class DateUtils {
    /**
     * Formatter for date and time in the format "d/M/yyyy HHmm".
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    /**
     * Formatter for date in ISO local date format "yyyy-MM-dd".
     */
    public static final DateTimeFormatter DATE_TIME_LOCAL_DATE = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Parses a date string into a {@code LocalDateTime} object.
     * The method first attempts to parse the string using the formatter for date and time.
     * If parsing fails, it then attempts to parse the string using the formatter for date only and sets the time to the start of the day.
     *
     * @param date The date string to parse.
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     * @throws DateTimeParseException If the date string cannot be parsed using either formatter.
     */
    public static LocalDateTime parseDateTime(String date) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(date, DATE_TIME_LOCAL_DATE).atStartOfDay();
        }
    }

}
