package seedu.avo.utils;

import seedu.avo.exceptions.AvoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private static String generateErrorMessage(String format, String input) {
        return String.format("Please provide your date format in %s instead of %s", format, input);
    }
    public static LocalDateTime parseWithTime(String input) throws AvoException {
        String format = "yyyy-MM-dd HH:mm";
        try {
            return LocalDateTime.parse(input.trim(), DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            throw new AvoException(generateErrorMessage(format, input));
        }
    }
    public static LocalDate parseWithoutTime(String input) throws AvoException {
        String format = "yyyy-mm-dd";
        try {
            return LocalDate.parse(input.trim());
        } catch (DateTimeParseException e) {
            throw new AvoException(generateErrorMessage(format, input));
        }
    }
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
