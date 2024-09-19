package chacha.parser;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides methods for parsing time strings into LocalTime objects.
 */
public class TimeParser {
    /**
     * Parses a time string into a LocalTime object.
     *
     * @param str The time string to be parsed.
     * @return LocalTime object
     */
    public static LocalTime parseStringToTime(String str) {
        try {
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("h[.mm]a");
            return LocalTime.parse(str, parser);
        } catch (DateTimeException e) {
            return null;
        }
    }

    /**
     * Parses a LocalTime object to a time string.
     *
     * @param time The LocalTime object to be parsed.
     * @return Formatted time string.
     */
    public static String parseTimeToString(LocalTime time) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("h[.mm]a");
        return time.format(parser).toUpperCase();
    }
}
