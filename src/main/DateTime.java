package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public static final DateTimeFormatter dmy = DateTimeFormatter.ofPattern("dd MMM yyyy");
    public static final LocalDate parseDate(String date) {
        return LocalDate.parse(date);  // parse the date in format yyyy-mm-dd
    }
}
