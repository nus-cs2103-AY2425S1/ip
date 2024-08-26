import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

    public static LocalDateTime parse(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeString.trim(), INPUT_FORMATTER);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMATTER);
    }
}
