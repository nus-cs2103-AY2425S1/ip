package bottleopener;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Util {
    public static final DateTimeFormatter FORMATTER;

    static {
        FORMATTER = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
    }

}
