package alice.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Handles datetime and string conversions. */
public class DateParser {
    private static final DateTimeFormatter[] DATETIME_FORMATTERS = new DateTimeFormatter[]{
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
    };

    /**
     * Parses a datetime in yyyy-MM-dd HH:mm, yyyy-MM-dd HHmm,
     * dd-MM-yyyy HH:mm or dd-MM-yyyy HHmm format.
     *
     * @param  dateString             the date string to parse
     * @return                        the parsed LocalDateTime
     * @throws DateTimeParseException if the date string is not well=formed
     */
    public static LocalDateTime parseDateString(String dateString) throws DateTimeParseException {
        for (DateTimeFormatter formatter : DATETIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateString, formatter);
            } catch (DateTimeParseException exception) {
                // try another format
            }
        }
        return LocalDateTime.parse(dateString);
    }

    /**
     * Converts a datetime to string
     * in yyyy-MM-dd HH:mm format.
     *
     * @param  datetime the datetime to convert
     * @return          the formatted datetime string
     */
    public static String toDateString(LocalDateTime datetime) {
        return DATETIME_FORMATTERS[0].format(datetime);
    }
}
