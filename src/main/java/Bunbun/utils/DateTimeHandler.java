package Bunbun.utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class DateTimeHandler {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate isValidLocalDate(String dateStr) {

        LocalDate date = null;
        try {
            date = LocalDate.parse(dateStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return date;
        }
        return date;
    }
}
