package kafka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class provides utility methods for converting LocalDateTime objects to and from different string formats.
 */
public class LocalDateTimeConverter {

    /**
     * Parses a string representing a LocalDateTime in the format "yyyy-MM-dd HHmm" into a LocalDateTime object.
     *
     * @param dateTime The string representing the LocalDateTime in the format "yyyy-MM-dd HHmm".
     * @return The parsed LocalDateTime object.
     */
    public static LocalDateTime getLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Formats a LocalDateTime object into a string in the format "MMMM dd yyyy HHmm".
     *
     * @param ldt The LocalDateTime object to be formatted.
     * @return The formatted string in the format "MMMM dd yyyy HHmm".
     */
    public static String getDifferentFormat(LocalDateTime ldt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy HHmm");
        return ldt.format(formatter);
    }
}
