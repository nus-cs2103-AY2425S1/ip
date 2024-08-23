import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeHandler {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    public static LocalDateTime parse(String dateTime) throws DateTimeParseException {
        return LocalDateTime.parse(dateTime, INPUT_FORMATTER);
    }

    public static String format(LocalDateTime dateTime, boolean fileFormat) {
        if (fileFormat) {
            return dateTime.format(INPUT_FORMATTER);
        } else {
            return dateTime.format(OUTPUT_FORMATTER);
        }
    }

    // Overloaded method with default value
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, false);
    }

}
