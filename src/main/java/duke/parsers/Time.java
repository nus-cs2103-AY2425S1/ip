/**
 * This class converts string format of date to local date time
*/
package duke.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.InvalidDateException;
import java.time.format.DateTimeParseException;

public class Time {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDate date;
    private LocalDateTime dateTime;
    private boolean hasTime;
    private String originalFormat;

    public Time(String dateTimeString) throws InvalidDateException {
            parseDateTime(dateTimeString);
    }

    /**
     * converts string format of date to localdate
     * if time is included, convert to localdatetime
     *
     * @param dateTimeString time input from user
     */
    private void parseDateTime(String dateTimeString) throws InvalidDateException {
        try {
            this.dateTime = LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
            this.hasTime = true;
            this.originalFormat = dateTimeString;
        } catch (DateTimeParseException e1) {
            try {
                this.date = LocalDate.parse(dateTimeString, DATE_FORMATTER);
                this.hasTime = false;
                this.originalFormat = dateTimeString;
            } catch (DateTimeParseException e2) {
                throw new InvalidDateException();
            }
        }
    }

    @Override
    public String toString() {
        return originalFormat;
    }

}
