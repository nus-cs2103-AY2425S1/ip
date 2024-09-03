package seedu.avo.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.avo.exceptions.AvoException;

/**
 * Represents a helper class to handle datetime
 */
public class DateTime {
    private static String generateErrorMessage(String format, String input) {
        return String.format("Please provide your date format in %s instead of %s", format, input);
    }

    /**
     * Obtains an instance of LocalDateTime from a text string in yyyy-MM-dd HH:mm
     * @param input A datetime in string format
     * @return A LocalDateTime object with the format in yyyy-MM-dd HH:mm
     * @throws AvoException The exception when time parsing fails
     */
    public static LocalDateTime parseWithTime(String input) throws AvoException {
        String format = "yyyy-MM-dd HH:mm";
        try {
            return LocalDateTime.parse(input.trim(), DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            throw new AvoException(generateErrorMessage(format, input));
        }
    }
    /**
     * Obtains an instance of LocalDateTime from a text string in yyyy-mm-dd
     * @param input A datetime in string format
     * @return A LocalDateTime object with the format in yyyy-mm-dd
     * @throws AvoException The exception when time parsing fails
     */
    public static LocalDate parseWithoutTime(String input) throws AvoException {
        String format = "yyyy-mm-dd";
        try {
            return LocalDate.parse(input.trim());
        } catch (DateTimeParseException e) {
            throw new AvoException(generateErrorMessage(format, input));
        }
    }
    /**
     * Obtains an instance of LocalDateTime from a text string in MMM d yyyy HHmm
     * @param input A datetime in string format
     * @return A LocalDateTime object with the format in MMM d yyyy HHmm
     * @throws AvoException The exception when time parsing fails
     */
    public static LocalDateTime parseFromStorage(String input) throws AvoException {
        String format = "MMM d yyyy HHmm";
        try {
            return LocalDateTime.parse(input.trim(), DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            throw new AvoException(generateErrorMessage(format, input));
        }
    }
    public static String format(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
