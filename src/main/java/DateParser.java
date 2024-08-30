import java.time.LocalDateTime
        ;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    public static LocalDateTime parse(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format.");
            return null;
        }
    }

    public static LocalDateTime parseFile(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, OUTPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format.");
            return null;
        }
    }
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMATTER);
    }
}
