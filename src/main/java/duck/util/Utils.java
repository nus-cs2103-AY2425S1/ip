package duck.util;

import duck.data.exception.DuckException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class providing helper methods for handling date-time conversion and validation.
 */
public class Utils {

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
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

        try {
            // Try to parse the input string using the first format
            return LocalDateTime.parse(dateTimeStr, formatter1);
        } catch (DateTimeParseException e) {
            // If it fails, try to parse using the second format
            try {
                return LocalDateTime.parse(dateTimeStr, formatter2);
            } catch (DateTimeParseException ex) {
                // If both fail, throw a Duck.Duck.data.exception.DuckException
                throw new DuckException("""
                        Invalid date format following the command /from /to /by!
                        Please use one of the following formats:
                        yyyy-MM-dd HHmm or yyyy/MM/dd HHmm
                       """);
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
}
