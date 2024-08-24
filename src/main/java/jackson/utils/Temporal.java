package jackson.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * Class used to handle LocalDate and LocalDateTime objects.
 * This is required as we want to allow flexibility in whether the user defines
 * a time or not, and both LocalDate and LocalDateTime strictly require no timing
 * and timing respectively
 */
public class Temporal {
    // DateTimeFormatters for reading (first 2) from save file and user input
    // and writing (last 2) to save file
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

    /* Stores either LocalDate or LocalDateTime */
    private LocalDate localDate;
    private LocalDateTime localDateTime;

    /**
     * Constructor for Temporal class.
     * @param dateTimeString String of dateTime to "parse"
     */
    public Temporal(String dateTimeString) {
        try {
            this.localDateTime = LocalDateTime.parse(dateTimeString, dateTime);
        } catch (DateTimeParseException e) {
            this.localDate = LocalDate.parse(dateTimeString, dateOnly);
        }
    }

    /**
     * String representation of {@code Temporal} object.
     * @return LocalDateTime or LocalDate String format depending on which is stored
     */
    @Override
    public String toString() {
        return localDate == null
                ? this.localDateTime.format(dateTimeOutput)
                : this.localDate.format(dateOnlyOutput);
    }
}
