package david.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import david.exceptions.DavidInvalidDateTimeException;
import david.exceptions.DavidInvalidTimeException;

/**
 * Date parser class to parse given date into proper LocalDateTime and back to String
 */
public class DateParser extends Parser {
    private static final String formatInputPattern = "yyyy-MM-dd HHmm";
    private static final String formatCachePattern = " yyyy-MM-dd HHmm";
    private static final String formatOutputPattern = "HHmm dd MMM yyyy";

    /**
     * Takes an input date time format in the form of " yyyy-MM-dd HHmm" and parses it into a LocalDateTime instance.
     *
     * @param date String date in "yyyy-MM-dd HHmm".
     * @return LocalDateTime instance.
     * @throws DavidInvalidDateTimeException if time is invalid.
     */
    public static LocalDateTime getDate(String date) throws DavidInvalidDateTimeException {
        try {
            //Remove leading and trailing whitespace
            String dateInput = date.trim();

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(formatInputPattern);
            LocalDateTime d = LocalDateTime.parse(dateInput, inputFormatter);

            return d;
        } catch (DateTimeParseException e) {
            throw new DavidInvalidDateTimeException();
        }
    }

    /**
     * Converts LocalDateTime instance into a readable output date
     *
     * @param date LocalDateTime.
     * @return Formatted string in the form of "HHmm dd MMM yyyy".
     */
    public static String formatOutputDate(LocalDateTime date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(formatOutputPattern);
        return date.format(outputFormatter);
    }

    /**
     * Converts LocalDateTime instance into a String format for caching
     *
     * @param date LocalDateTime.
     * @return Formatted string in the form of " yyyy-MM-dd HHmm".
     */
    public static String formatCacheDate(LocalDateTime date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(formatCachePattern);
        return date.format(outputFormatter);
    }

    /**
     * Validates if a given date time is before another given date time
     *
     * @param t1 LocalDateTime of the first time instance.
     * @param t2 LocalDateTime of the second time instance.
     * @return true if t1 is before t2.
     */
    public static boolean validateDateTime(LocalDateTime t1, LocalDateTime t2) throws DavidInvalidTimeException {
        if (t1.isAfter(t2) || t1.isEqual(t2)) {
            throw new DavidInvalidTimeException();
        }
        return true;
    }
}
