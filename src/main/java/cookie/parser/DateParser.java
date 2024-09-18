package cookie.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for parsing and formatting dates.
 */
public class DateParser {
    /**
     * Checks whether a given string is parsable by {@code LocalDate.parse}.
     *
     * @param date the date string to be checked
     * @return {@code true} if the date string is parsable, {@code false} otherwise
     */
    public static boolean checkValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Returns a LocalDate format of a given date.
     *
     * @param date to convert.
     * @return LocalDate if parsable, otherwise null.
     */

    public static LocalDate convertStringToDate(String date) {
        if (DateParser.checkValidDate(date)) {
            return LocalDate.parse(date);
        }
        return null;
    }

    /**
     * Converts a given date string to a {@code LocalDate} object.
     *
     * @param date the date string to convert
     * @return the {@code LocalDate} object if the date string is valid, {@code null} otherwise
     */
    public static String changePattern(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
