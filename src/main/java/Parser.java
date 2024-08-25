import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String DEFAULT_TIME = "2359";
    private static final String INPUT_FORMAT = "dd/MM/yyyy HHmm";
    private static final String OUTPUT_FORMAT = "d MMM yyyy HH:mm";

    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        String[] dateTimeParts = dateTime.trim().split(" ");

        if (dateTimeParts.length == 1) {
            dateTime = dateTime + " " + DEFAULT_TIME;
        }

        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(INPUT_FORMAT));
    }

    public static String formatDateTimeToOutput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_FORMAT));
    }

    public static String formatDateTimeToInput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(INPUT_FORMAT));
    }
}