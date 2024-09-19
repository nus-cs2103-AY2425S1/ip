package chacha.parser;

import java.time.DateTimeException;
import java.time.LocalDate;

import chacha.exception.WrongDateFormatException;

/**
 * Provides methods to parse date strings into LocalDate objects.
 */
public class DateParser {
    /**
     * Parses a date string into a LocalDate object.
     *
     * @param str The date string to be parsed.
     * @return LocalDate object
     * @throws WrongDateFormatException if the date format is wrong.
     */
    public static LocalDate parseDate(String str) throws WrongDateFormatException {
        try {
            return LocalDate.parse(str);
        } catch (DateTimeException e) {
            return null;
        }
    }
}
