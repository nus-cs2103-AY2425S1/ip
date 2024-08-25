package David.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser extends Parser{

    public static LocalDateTime getDate(String date) {
        //format string to remove whitespaces first
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
        LocalDateTime d = LocalDateTime.parse(date, inputFormatter);
        return d;
    }

    public static String formatOutputDate(LocalDateTime date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HHmm dd MMM yyyy");
        return date.format(outputFormatter);
    }

    public static String formatCacheDate(LocalDateTime date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
        return date.format(outputFormatter);
    }
}
