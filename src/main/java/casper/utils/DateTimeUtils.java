package casper.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import casper.exceptions.InvalidDateTimeException;


/**
 * Utility class for handling date and time conversions.
 */
public class DateTimeUtils {
    /**
     * Converts a string representing date and time to a {@code LocalDateTime} object.
     *
     * @param dateTimeString A string representing date and time in the format "dd-MM-yyyy HH:mm".
     * @return A {@code LocalDateTime} object representing the provided date and time.
     * @throws InvalidDateTimeException If the provided string cannot be parsed into a valid date and time.
     */
    public static LocalDateTime convertStringToDateTime(String dateTimeString) throws InvalidDateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Converts a {@code LocalDateTime} object to a string representation.
     *
     * @param dateTime A {@code LocalDateTime} object to be converted to a string.
     * @return A string representation of the provided date and time in the format "dd-MM-yyyy HH:mm".
     */
    public static String convertDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateTime.format(formatter);
    }

}
