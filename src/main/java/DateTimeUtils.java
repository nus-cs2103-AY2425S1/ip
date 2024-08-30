import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtils {
    private static final DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public static LocalDateTime parseDataTime(String dateTime) throws InvalidTimeFormat {
        try {
            return LocalDateTime.parse(dateTime, INPUT);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormat("Please provide valid time format in yyyy-MM-dd HHmm");
        }
    }

    public static String formatDateTime(LocalDateTime dateTime) throws DateTimeException {
        return dateTime.format(OUTPUT);
    }
}
