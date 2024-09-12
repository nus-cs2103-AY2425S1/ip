package duke.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.InvalidDateException;


/**
 * This class converts string format of date to local date time
 */
public class Time {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDate date;
    private LocalDateTime dateTime;
    private boolean hasTime;
    private String originalFormat;

    /**
     * Constructs a `Time` object with the specified date and time.
     *
     * @param dateTimeString The date and time input from user.
     */
    public Time(String dateTimeString) throws InvalidDateException {
        parseDateTime(dateTimeString);
    }
    private void parseDateTime(String dateTimeString) throws InvalidDateException {
        if (tryParseDateTime(dateTimeString)) {
            return;
        }
        if (tryParseDate(dateTimeString)) {
            return;
        }
        throw new InvalidDateException();
    }

    private boolean tryParseDateTime(String dateTimeString) {
        try {
            this.dateTime = LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
            this.hasTime = true;
            this.originalFormat = dateTimeString;
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean tryParseDate(String dateTimeString) {
        try {
            this.date = LocalDate.parse(dateTimeString, DATE_FORMATTER);
            this.hasTime = false;
            this.originalFormat = dateTimeString;
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return originalFormat;
    }

}
