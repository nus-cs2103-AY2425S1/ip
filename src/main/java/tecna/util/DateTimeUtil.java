package tecna.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateTimeUtil {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    // Private constructor to prevent instantiation
    private DateTimeUtil() {

    }

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }
}
