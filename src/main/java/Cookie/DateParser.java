package Cookie;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public static LocalDate convertStringToDate(String date) {
        if (DateParser.isValidDate(date)) {
            return LocalDate.parse(date);
        }
        return null;
    }

    public static String changePattern(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
