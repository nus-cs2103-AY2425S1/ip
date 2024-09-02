package joe.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Formatter class that formats date and time.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Formatter {
    private static final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMM/d/yyyy HH:mm");
    /**
     * Formats a LocalDateTime object to a String.
     *
     * @param dateTime the LocalDateTime object to be formatted
     * @return a String representing the formatted LocalDateTime object
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(parseFormatter);
    }
    /**
     * Creates a LocalDateTime object with an arbitrary time.
     *
     * @param date the date to be formatted
     * @return a LocalDateTime object with an arbitrary time
     */
    public static LocalDateTime createLocalDateTimeWithArbitraryTime(String date) {
        return LocalDateTime.parse(date.strip() + " 1200", parseFormatter);
    }

    /**
     * Parses a date and time string to a LocalDateTime object.
     * @param dateTime the date and time string to be parsed
     * @return a LocalDateTime object representing the date and time string
     */
    public static LocalDateTime parseDateTimeString(String dateTime) {
        return LocalDateTime.parse(dateTime, parseFormatter);
    }

    /**
     * Prints a LocalDateTime object to a formatted string.
     * @param dateTime the LocalDateTime object to be printed
     * @return a formatted string representing the LocalDateTime object
     */
    public static String printDateTime(LocalDateTime dateTime) {
        return dateTime.format(printFormatter);
    }
}
