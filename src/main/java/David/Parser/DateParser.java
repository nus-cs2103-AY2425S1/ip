package David.Parser;

import David.Exceptions.DavidInvalidDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser extends Parser{

    public static LocalDateTime getDate(String date) throws DavidInvalidDateTimeException{
        try {
            //format string to remove whitespaces first
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
            LocalDateTime d = LocalDateTime.parse(date, inputFormatter);
            return d;
        } catch (DateTimeParseException e) {
            throw new DavidInvalidDateTimeException();
        }
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
