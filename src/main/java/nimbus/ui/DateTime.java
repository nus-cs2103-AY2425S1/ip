package nimbus.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nimbus.exception.WrongDateTimeFormatException;

/**
 * This class handles the formatting of dates amd times
 */
public class DateTime {
    private LocalDateTime dateTime;

    /**
     * Creates DateTime object that changes the userInput into the desired output string
     * for start time and deadlines
     *
     * @param dateTime the date time user has provided
     * @throws WrongDateTimeFormatException if the userInput is in the wrong form
     */
    public DateTime(String dateTime) throws WrongDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.dateTime = LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException();
        }
    }

    /**
     * Creates DateTime object that changes the userInput into the desired output string for endTime
     *
     * @param startTime start time of event task
     * @param endTime end time of event task
     * @throws WrongDateTimeFormatException if the userInput is in the wrong form
     */
    public DateTime(String startTime, String endTime) throws WrongDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);

            if (endTime.contains("/")) {
                this.dateTime = LocalDateTime.parse(endTime, formatter);
            } else {
                String combinedDateTime = startDateTime.toLocalDate()
                        .format(DateTimeFormatter.ofPattern("d/M/yyyy")) + " " + endTime;
                this.dateTime = LocalDateTime.parse(combinedDateTime, formatter);
            }
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException();
        }
    }

    /**
     * gets dateTime
     *
     * @return LocalDateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * changes dateTime string into storage format
     *
     * @return String in storage format
     */
    public String toStorageFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return dateTime.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return dateTime.format(formatter);
    }
}
