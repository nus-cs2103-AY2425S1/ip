package main.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task is a class that represents tasks that a user may input.
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * A constructor for Task.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    private String getStatusNum() {
        return (isDone ? "1" : "0");
    }

    /**
     * Mark the task of the user as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task of the user as incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a formatted date and/or time.
     * Converts date to dd mmm yyy, eg. 15 Aug 2024.
     * Converts time to h:mm, eg. 6:00 pm.
     * @param input A String representing a date and/or time.
     * @return A formatted date and/or time.
     */
    protected String formatDate(String input) {
        if (isDateFormat(input)) {
            return parseDate(input);
        } else if (isTimeFormat(input)) {
            return parseTime(input);
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
     * Parses the input to return a formatted date.
     * @param input Input by the user.
     * @return String representing a date (eg. 8 Sep 2024)
     */
    private String parseDate(String input) {
        LocalDate date = LocalDate.parse(input);
        String formatted = date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return formatted;
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

    /**
     * Parses the input to return a formatted date and time.
     * @param input Input by the user.
     * @return String representing a date and time (eg. 8 Sep 2024 11:31pm)
     */
    private String parseTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        String formatted = dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
        return formatted;
    }

    /**
     * Returns a string representing the format used for storage of input in files.
     * @return String.
     */
    public String toFileFormat() {
        return this.getStatusNum() + " .. " + this.getDescription();
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
