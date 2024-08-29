package parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code DateTimeHandler} class provides utility methods for converting and formatting
 * date and time values. It includes methods to convert a date-time string into a {@code LocalDateTime}
 * object and to format a {@code LocalDateTime} object into a specific string pattern.
 */
public class DateTimeHandler {

    /**
     * Converts a date-time string into a {@code LocalDateTime} object. The input string should be in the
     * format "YYYY/MM/DD HHMM". If the time part is not provided, it defaults to midnight (00:00).
     *
     * @param input The date-time string to be converted.
     * @return The corresponding {@code LocalDateTime} object.
     * @throws NumberFormatException If the input string contains invalid numerical values.
     * @throws DateTimeException If the input string represents an invalid date or time.
     */
    public static LocalDateTime convertToDateTime(String input) {
        String[] parts = input.split(" ");

        // Parse the date part
        String[] dateParts = parts[0].split("/");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        LocalDate date = LocalDate.of(year, month, day);
        LocalTime time;

        // Parse the time part
        if (parts.length == 2) {
            String timePart = parts[1];
            int hour = Integer.parseInt(timePart.substring(0, 2));
            int minute = Integer.parseInt(timePart.substring(2, 4));
            time = LocalTime.of(hour, minute);
        } else {
            time = LocalTime.MIDNIGHT;
        }
        return LocalDateTime.of(date, time);
    }

    /**
     * Formats a {@code LocalDateTime} object into a string with the pattern "MMM dd yyyy HHmm".
     *
     * @param time The {@code LocalDateTime} object to be formatted.
     * @return The formatted date-time string.
     */
    public static String formatDateTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return time.format(formatter);
    }
}