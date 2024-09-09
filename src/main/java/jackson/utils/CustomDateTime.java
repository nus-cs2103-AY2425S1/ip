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
 * and timing respectively.
 */
public class CustomDateTime implements Comparable<CustomDateTime> {
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
     * Constructs Temporal instance.
     * @param dateTimeString String of dateTime to "parse".
     */
    public CustomDateTime(String dateTimeString) {
        try {
            this.localDateTime = LocalDateTime.parse(dateTimeString, dateTime);
        } catch (DateTimeParseException e) {
            // if exception thrown, means dateTimeString only contains date
            this.localDate = LocalDate.parse(dateTimeString, dateOnly);
        }
    }

    /**
     * Returns whether a CustomDateTime object has time or not.
     * @return true if time is present, otherwise false.
     */
    public boolean hasTime() {
        return localDateTime != null;
    }

    /**
     * Overrides {@code compareTo} and compares CustomDateTime objects.
     * If other/this is null, return the non-null object.
     * If both are not null, compare both localDates.
     * @param other the object to be compared.
     * @return < 0 if this < other, == 0 if this == other, > 1 if this > other.
     */
    @Override
    public int compareTo(CustomDateTime other) {
        if (other == null) {
            return -1;
        } else if (other.hasTime() == this.hasTime()) {
            return other.hasTime()
                    ? this.localDateTime.compareTo(other.localDateTime)
                    : this.localDate.compareTo(other.localDate);
        } else if (other.hasTime()) {
            LocalDate otherDate = other.localDateTime.toLocalDate();
            return this.localDate.equals(otherDate)
                    ? 1
                    : this.localDate.compareTo(otherDate);
        } else {
            LocalDate thisDate = this.localDateTime.toLocalDate();
            return thisDate.equals(other.localDate)
                    ? -1
                    : thisDate.compareTo(other.localDate);
        }
    }

    /**
     * String representation of {@code Temporal} object.
     * @return LocalDateTime or LocalDate String format depending on which is stored.
     */
    @Override
    public String toString() {
        return localDate == null
                ? this.localDateTime.format(dateTimeOutput)
                : this.localDate.format(dateOnlyOutput);
    }
}
