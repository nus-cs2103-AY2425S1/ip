package chatkaki;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a helper class for date and time operations.
 */
public class DateTimeHelper {
    private static final String DATE_FORMAT = "d/M/yyyy HHmm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Parses the date string to LocalDateTime.
     *
     * @param date The date string to be parsed.
     * @return The LocalDateTime object.
     */
    public static LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, FORMATTER);
        } catch (Exception e) {
            Ui.printMessage("Invalid date format, it should be " + DATE_FORMAT);
            return null;
        }
    }

    /**
     * Checks if the date string is in the correct format.
     *
     * @param date The date string to be checked.
     * @return True if the date string is in the correct format, false otherwise.
     */
    public static boolean isValidDateFormat(String date) {
        try {
            LocalDateTime.parse(date, FORMATTER);
            return true;
        } catch (Exception e) {
            Ui.printMessage("Invalid date format, it should be " + DATE_FORMAT);
            return false;
        }
    }
}
