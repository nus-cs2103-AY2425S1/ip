package tecna.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Handles the DateTime format procedures used in the app.
 *
 * @author DennieDan.
 */
public class DateTimeUtil {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    // Private constructor to prevent instantiation
    private DateTimeUtil() {

    }

    /**
     * Parses the string of datetime to a LocalDateTime object.
     *
     * @param dateTime String of date time.
     * @return LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }
}
