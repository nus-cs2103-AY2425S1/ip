package chatbot.impl.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    public static final DateTimeFormatter dmy = DateTimeFormatter.ofPattern("dd MMM yyyy");
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date);  // parse the date in format yyyy-mm-dd
    }
}