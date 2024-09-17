package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.BrockException;

/**
 * Class with commonly used utility functions for storage
 *      to reduce code duplication.
 */
public class StorageUtility {
    /**
     * Removes closing bracket from the body string of each task.
     * So that the correct date or time can be extracted for deadline and event tasks.
     *
     * @param target String fragment with closing bracket to be removed.
     * @return String without the closing bracket.
     */
    public static String removeCloseBracket(String target) throws BrockException {
        int length = target.length();
        char lastChar = target.charAt(length - 1);
        if (lastChar != ')') {
            throw new BrockException("Invalid deadline/event entry - missing closing bracket!");
        }
        // Substring from start to the second last index
        return target.substring(0, length - 1);
    }

    /**
     * Parses date from "MMM dd yyyy format" into "yyyy-mm-dd" format.
     *
     * @param targetDate Date string to be parsed.
     * @return Parsed date string.
     */
    public static String parseDate(String targetDate) throws BrockException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            return LocalDate.parse(targetDate, dateFormatter)
                    .toString();
        } catch (DateTimeParseException e) {
            throw new BrockException("Invalid deadline/event entry - date format is wrong!");
        }
    }
}
