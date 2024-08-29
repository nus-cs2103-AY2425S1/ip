import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Parser {
    private static final DateTimeFormatter dateInputFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter dateTextFormatter
            = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public static LocalDateTime parseDateString(String input)
            throws IllegalArgumentException {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String[] tokens = input.split(" ");
        String date = tokens[0];
        String time = tokens.length == 2 ? tokens[1] : "";

        if (time.isEmpty()) {
            return LocalDateTime.parse(date + " " + "00:00", dateInputFormatter);
        } else {
            return LocalDateTime.parse(date + " " + time, dateInputFormatter);
        }
    }

    public static String parseLocalDateTime(LocalDateTime date) {
        return date.format(dateInputFormatter);
    }

    public static String displayDate(LocalDateTime date) {
        return date.format(dateTextFormatter);
    }
}
