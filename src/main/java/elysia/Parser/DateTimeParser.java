package elysia.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    public static String parseDate(String rawDate) throws DateTimeParseException {
        LocalDate parsedDate = LocalDate.parse(rawDate);
        String date = parsedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return date;
    }
}
