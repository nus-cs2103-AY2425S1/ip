package main.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event represents an event task and is a subclass of the Task class.
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * A constructor for Event.
     * @param description Description of the event as inputted by the user.
     * @param from        Date and/or time representing the start of the event.
     * @param to          Date and/or time representing the end of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted date and/or time.
     * Converts date to dd mmm yyy, eg. 15 Aug 2024.
     * Converts time to h:mm, eg. 6:00 pm.
     * @param input A String representing a date and/or time.
     * @return A formatted date and/or time.
     */
    private String formatDate(String input) {
        if (isDateFormat(input)) {
            LocalDate date = LocalDate.parse(input);
            String formatted = date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            return formatted;
        } else if (isTimeFormat(input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            String formatted = dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
            return formatted;
        } else {
            return input;
        }
    }

    /**
     * Returns a boolean if the inputted date is in the specified format.
     * Checks if the date is in a particular format yyyy-mm-dd.
     * @param input String representing a date.
     * @return Boolean.
     */
    private boolean isDateFormat(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns a boolean if the inputted date and time is in the specified format.
     * Checks if the time is in a particular format yyyy-mm-dd hhmm.
     * @param input String representig a date.
     * @return Boolean.
     */
    private boolean isTimeFormat(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toFileFormat() {
        return "E .. " + super.toFileFormat() + " .. " + this.from + " .. " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(this.from)
                + " to: " + formatDate(this.to) + ")";
    }
}
