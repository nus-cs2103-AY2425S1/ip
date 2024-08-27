import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    private static final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMM/d/yyyy HH:mm");

    public static String formatToDo(Task t) {
        return String.format("T | %d | %s", t.isDone() ? 1 : 0, t.getDescription());
    }

    public static String formatDeadline(Deadline d) {
        return String.format("D | %d | %s | by %s",
                d.isDone() ? 1 : 0,
                d.getDescription(),
                formatDateTime(d.getDue())
        );
    }

    public static String formatEvent(Event e) {
        return String.format("E | %d | %s | from %s | to %s",
                e.isDone() ? 1 : 0,
                e.getDescription(),
                formatDateTime(e.getStart()),
                formatDateTime(e.getEnd())
        );
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(parseFormatter);
    }

    public static LocalDateTime createLocalDateTimeWithArbitraryTime(String date) {
        return LocalDateTime.parse(date.strip() + " 1200", parseFormatter);
    }

    public static LocalDateTime parseDateTimeString(String dateTime) {
        return LocalDateTime.parse(dateTime, parseFormatter);
    }

    public static String printDateTime(LocalDateTime dateTime) {
        return dateTime.format(printFormatter);
    }
}
