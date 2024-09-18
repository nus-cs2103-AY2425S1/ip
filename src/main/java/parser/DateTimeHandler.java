package parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import exceptions.InvalidFormatException;

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
    public static LocalDateTime convertToDateTime(String input) throws InvalidFormatException {
        String[] parts = input.trim().split(" ");

        if (parts.length == 0 || !parts[0].contains("/")) {
            throw new InvalidFormatException("Invalid date format. Please use YYYY/MM/DD.");
        }

        String[] dateParts = parts[0].split("/");
        if (dateParts.length != 3) {
            throw new InvalidFormatException("Date must be in the format YYYY/MM/DD.");
        }

        int year;
        int month;
        int day;
        try {
            year = Integer.parseInt(dateParts[0]);
            month = Integer.parseInt(dateParts[1]);
            day = Integer.parseInt(dateParts[2]);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Year, month, and day must be valid integers.");
        }

        LocalDate date;
        try {
            date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new InvalidFormatException("Invalid date. Please check the year, month, and day values.");
        }

        LocalTime time = LocalTime.MIDNIGHT; //Default time to midnight

        if (parts.length == 2) {
            String timePart = parts[1].trim();

            if (timePart.length() != 4) {
                throw new InvalidFormatException("Invalid time format. Please use HHMM.");
            }

            int hour;
            int minute;
            try {
                hour = Integer.parseInt(timePart.substring(0, 2));
                minute = Integer.parseInt(timePart.substring(2, 4));
            } catch (NumberFormatException e) {
                throw new InvalidFormatException("Hour and minute must be valid integers.");
            }

            try {
                time = LocalTime.of(hour, minute);
            } catch (DateTimeException e) {
                throw new InvalidFormatException("Invalid time. Please check the hour and minute values.");
            }
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
