package lawrence.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class used to standardise how dates are stored and displayed.
 */
public class DateParser {
    public static final String FORMAT_STRING_FOR_USER_INPUT = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_STRING_FOR_STORAGE = "dd-MM-yyyy HH:mm";
    private static final DateTimeFormatter FORMATTER_FOR_USER_INPUT = DateTimeFormatter
            .ofPattern(FORMAT_STRING_FOR_USER_INPUT);
    private static final DateTimeFormatter FORMATTER_FOR_STORAGE = DateTimeFormatter
            .ofPattern(FORMAT_STRING_FOR_STORAGE);

    /**
     * Converts user input into a {@link LocalDateTime} object.
     * Formatting is based on {@link #FORMAT_STRING_FOR_USER_INPUT}.
     *
     * @param input the string to convert to a {@link LocalDateTime} object
     * @return a {@link LocalDateTime} object
     */
    public static LocalDateTime parseUserInputDate(String input) {
        return LocalDateTime.parse(input, FORMATTER_FOR_USER_INPUT);
    }

    /**
     * Converts file input into a {@link LocalDateTime} object.
     * Formatting is based on {@link #FORMAT_STRING_FOR_STORAGE}.
     *
     * @param input the string to convert to a {@link LocalDateTime} object
     * @return a {@link LocalDateTime} object
     */
    public static LocalDateTime parseFileInputDate(String input) {
        return LocalDateTime.parse(input, FORMATTER_FOR_STORAGE);
    }

    /**
     * Converts a {@link LocalDateTime} object into a string.
     * Formatting is based on {@link #FORMAT_STRING_FOR_STORAGE}.
     *
     * @param input the {@link LocalDateTime} object to be converted to a string
     * @return a string representing the {@link LocalDateTime} object
     */
    public static String toOutputString(LocalDateTime input) {
        return input.format(FORMATTER_FOR_STORAGE);
    }
}
