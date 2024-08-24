package chatkaki;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
    private static final String DATE_FORMAT = "d/M/yyyy HHmm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, FORMATTER);
        } catch (Exception e) {
            Ui.printMessage("Invalid date format, it should be " + DATE_FORMAT);
            return null;
        }
    }

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
