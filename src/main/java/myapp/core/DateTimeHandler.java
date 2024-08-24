package myapp.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for handling date and time parsing and formatting within the BingBong application.
 */
public class DateTimeHandler {

    private static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    /**
     * Parses a string representation of a date and time into a {@link LocalDateTime} object.
     *
     * @param dateTime the date and time string to be parsed, in the format "d/M/yyyy HHmm".
     * @return a {@link LocalDateTime} object representing the parsed date and time.
     * @throws DateTimeParseException if the text cannot be parsed.
     */
    public static LocalDateTime parse(String dateTime) throws DateTimeParseException {
        return LocalDateTime.parse(dateTime, INPUT_DATETIME_FORMATTER);
    }

    /**
     * Parses a string representation of a date into a {@link LocalDate} object.
     *
     * @param date the date string to be parsed, in the format "d/M/yyyy".
     * @return a {@link LocalDate} object representing the parsed date.
     * @throws DateTimeParseException if the text cannot be parsed.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, INPUT_DATE_FORMATTER);
    }

    /**
     * Formats a {@link LocalDateTime} object into a string.
     *
     * @param dateTime the {@link LocalDateTime} to be formatted.
     * @param fileFormat if true, formats using the input format ("d/M/yyyy HHmm");
     *                   if false, formats using the output format ("d MMM yyyy, h:mm a").
     * @return a formatted date and time string.
     */
    public static String format(LocalDateTime dateTime, boolean fileFormat) {
        if (fileFormat) {
            return dateTime.format(INPUT_DATETIME_FORMATTER);
        } else {
            return dateTime.format(OUTPUT_DATETIME_FORMATTER);
        }
    }

    /**
     * Formats a {@link LocalDateTime} object into a string using the default output format.
     *
     * @param dateTime the {@link LocalDateTime} to be formatted.
     * @return a formatted date and time string.
     */
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, false);
    }

    /**
     * Formats a {@link LocalDate} object into a string using the output date format ("d MMM yyyy").
     *
     * @param date the {@link LocalDate} to be formatted.
     * @return a formatted date string.
     */
    public static String format(LocalDate date) {
        return date.format(OUTPUT_DATE_FORMATTER);
    }
}
