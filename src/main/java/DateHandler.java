import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHandler {
    private static final DateTimeFormatter[] formatters = {
        DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
        DateTimeFormatter.ofPattern("MM/dd/yyyy"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };

    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public static LocalDateTime parseLocalDateTime(String date) {
        LocalDateTime localDateTime = null;

        for (DateTimeFormatter formatter : formatters) {
            try {
                localDateTime = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeException ignored) {}
        }
        return localDateTime;
    }

    public static LocalDate parseLocalDate(String date) {
        LocalDate localDate = null;

        for (DateTimeFormatter formatter : formatters) {
            try {
                localDate = LocalDate.parse(date, formatter);
                break;
            } catch (DateTimeException ignored) {}
        }
        return localDate;
    }
}
