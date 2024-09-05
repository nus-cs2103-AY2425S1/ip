package mylo.utils.formatters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for formatting date and time.
 * <p></p>
 * <p>This class provides methods to format date and time for display
 * and for storage in a specific string format.</p>
 *
 * @author cweijin
 */
public class Formatter {
    /**
     * Formats the given {@code LocalDateTime} for display.
     *
     * @param dateTime The {@code LocalDateTime} to be formatted.
     * @return A {@code String} representing the formatted date and time
     *         in the pattern "EEE dd MMM yyyy HH:mm".
     */
    public static String dateTimeDisplay(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm"));
    }

    /**
     * Formats the given {@code LocalDateTime} for storage.
     *
     * @param dateTime The {@code LocalDateTime} to be formatted.
     * @return A {@code String} representing the formatted date and time
     *         in the pattern "dd-LL-yyyy HHmm" suitable for storage.
     */
    public static String dateTimeStorage(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-LL-yyyy HHmm"));
    }
}
