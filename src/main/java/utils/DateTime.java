package utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public static final DateTimeFormatter DateTimePrintOutputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
    public static final DateTimeFormatter DateTimeFileOutputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static LocalDateTime formatDate(String dateTime) throws DateTimeException {
        String[] dateTimes = dateTime.split(" ");
        LocalDate date = LocalDate.parse(dateTimes[0]);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(dateTimes[1], timeFormatter);
        return LocalDateTime.of(date, time);
    }
}
