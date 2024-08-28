import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public static final DateTimeFormatter DATE_TIME_LOCAL_DATE = DateTimeFormatter.ISO_LOCAL_DATE;

    public static LocalDateTime parseDateTime(String date) throws DateTimeParseException {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE;

        try {
            return LocalDateTime.parse(date, formatter1);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(date, formatter2).atStartOfDay();
        }
    }

}
