import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    private static final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMM/d/yyyy HH:mm");

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(parseFormatter);
    }

    public static LocalDateTime createLocalDateTimeWithArbitraryTime(String date) {
        return LocalDateTime.parse(date.strip() + " 1200", parseFormatter);
    }

    public static LocalDateTime parseDateTimeString(String dateTime) {
        return LocalDateTime.parse(dateTime, parseFormatter);
    }

    public static String printDateTime(LocalDateTime dateTime) {
        return dateTime.format(printFormatter);
    }
}
