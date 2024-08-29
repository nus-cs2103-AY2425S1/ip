package utils.formatters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    public static String dateTimeDisplay(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm"));
    }

    public static String dateTimeStorage(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-LL-yyyy HHmm"));
    }
}
