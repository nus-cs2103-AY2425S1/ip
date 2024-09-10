package duck.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duck.data.exception.DuckException;

/**
 * Utility class providing helper methods for handling date-time conversion and validation.
 */
public class Utils {

    private static final String INVALID_DATE_FORMAT = """
             Invalid date format following the command /from /to /by!
             Please use one of the following formats:
             yyyy-MM-dd HHmm or yyyy/MM/dd HHmm
            """;
    private static final String DATE_FORMAT_ONE = "yyyy-MM-dd HHmm";
    private static final String DATE_FORMAT_TWO = "yyyy/MM/dd HHmm";

    /**
     * Private constructor to prevent instantiation.
     */
    private Utils() {
    }

    /**
     * Converts a string to a {@link LocalDateTime} object using predefined date-time formats.
     * Throws a {@link DuckException} if the string cannot be parsed with the accepted formats.
     *
     * @param dateTimeStr The date-time string to be converted.
     * @return The {@link LocalDateTime} object representing the provided string.
     * @throws DuckException If the string does not match the accepted date-time formats.
     */
    public static LocalDateTime convertToDateTime(String dateTimeStr) throws DuckException {
        // Define the two accepted date-time formats
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(DATE_FORMAT_ONE);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(DATE_FORMAT_TWO);

        try {
            // Try to parse the input string using the first format
            return LocalDateTime.parse(dateTimeStr, formatter1);
        } catch (DateTimeParseException e) {
            // If it fails, try to parse using the second format
            try {
                return LocalDateTime.parse(dateTimeStr, formatter2);
            } catch (DateTimeParseException ex) {
                // If both fail, throw a Duck.Duck.data.exception.DuckException
                throw new DuckException(INVALID_DATE_FORMAT);
            }
        }
    }

    /**
     * Checks if the given message is in the correct format for task update commands.
     * The format is expected to be "command index", where index is an integer.
     *
     * @param message The message to be checked.
     * @return {@code true} if the message is in the correct format, {@code false} otherwise.
     */
    public static boolean isCorrectUpdateFormat(String message) {
        String[] words = message.split(" ");
        return words.length == 2 && isInteger(words[1]);
    }

    /**
     * Checks if the given string can be parsed as an integer.
     *
     * @param str The string to be checked.
     * @return {@code true} if the string can be parsed as an integer, {@code false} otherwise.
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static int getTaskIndex(String message) throws DuckException {
        String[] words = message.split(" ");
        try {
            return Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DuckException(Message.INVALID_INDEX_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException(Message.INDEX_OUT_OF_BOUNDS);
        }
    }
}
