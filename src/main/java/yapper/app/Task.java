package yapper.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yapper.exceptions.YapperException;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private static String[] timeFormats = {
        "HHmm",
        "HH:mm[:ss]"
    };
    private static String[] dateFormats = {
        "d/M/yyyy",
        "d/MM/yyyy",
        "dd/M/yyyy",
        "dd/MM/yyyy",
        "yyyy/M/d",
        "yyyy/MM/d",
        "yyyy/M/dd",
        "yyyy/MM/dd",
    };

    private String description;
    private String isDone;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description the description of the task
     * @throws YapperException if the description is empty
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        if (description.isEmpty()) {
            throw new YapperException("Description cannot be empty");
        }
        this.description = description;
        this.isDone = "[ ]";
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = "[X]";
    }

    /**
     * Unmarks the task as not completed.
     */
    public void unmark() {
        this.isDone = "[ ]";
    }

    /**
     * Formats a date and/or time string into a human-readable format.
     *
     * The method first tries to parse the string as a date and time, then as a time only, and finally as a date only.
     * It returns the formatted date/time or the original string if it cannot be parsed.
     *
     * @param time the date and/or time string to be formatted
     * @return the formatted date/time string, or the original string if parsing fails
     */
    public String formattedDate(String time) {
        assert time != null : "Time should not be null";
        for (String timeFormat : timeFormats) {
            for (String dateFormat : dateFormats) {
                try {
                    String format = new StringBuilder(dateFormat).append(" ").append(timeFormat).toString();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                    LocalDateTime formattedDateTime = LocalDateTime.parse(time, formatter);
                    return formattedDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
                } catch (DateTimeParseException e) {
                    continue;
                }
            }
        }

        for (String timeFormat : timeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
                LocalTime formattedTime = LocalTime.parse(time, formatter);
                return formattedTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (String dateFormat : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                LocalDate formattedDate = LocalDate.parse(time, formatter);
                return formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return time;
    }

    /**
     * Returns the task description prefixed with its completion status.
     *
     * @return the task description with completion status
     */
    public String getDesc() {
        return this.isDone.charAt(1) + " | " + this.description;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return this.isDone + " " + this.description;
    }
}
