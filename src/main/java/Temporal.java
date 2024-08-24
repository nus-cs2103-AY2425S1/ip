import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Temporal {
    private LocalDate localDate;
    private LocalDateTime localDateTime;

    private static final DateTimeFormatter dateOnly = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            .toFormatter();
    private static final DateTimeFormatter dateTime = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy"))
            .toFormatter();
    private static final DateTimeFormatter dateOnlyOutput = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateTimeFormatter dateTimeOutput = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");

    public Temporal(String dateTimeString) {
        try {
            this.localDateTime = LocalDateTime.parse(dateTimeString, dateTime);
        } catch (DateTimeParseException e) {
            this.localDate = LocalDate.parse(dateTimeString, dateOnly);
        }
    }

    @Override
    public String toString() {
        return localDate == null
                ? this.localDateTime.format(dateTimeOutput)
                : this.localDate.format(dateOnlyOutput);
    }
}
