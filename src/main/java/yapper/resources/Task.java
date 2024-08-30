package yapper.resources;

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
    private String desc;
    private String isDone;

    @SuppressWarnings("checkstyle:DeclarationOrder")
    private static String[] timeFormats = {
        "HHmm",
        "HH:mm[:ss]"
    };

    @SuppressWarnings("checkstyle:DeclarationOrder")
    private static String[] dateFormats = {
        "d/M/yyyy", // Date only
        "d/MM/yyyy",
        "dd/M/yyyy",
        "dd/MM/yyyy",
        "yyyy/M/d",
        "yyyy/MM/d",
        "yyyy/M/dd",
        "yyyy/MM/dd",
    };

    /**
     * Constructs a new Task with the specified description.
     *
     * @param desc the description of the task
     * @throws YapperException if the description is empty
     */
    public Task(String desc) {
        if (desc.isEmpty()) {
            throw new YapperException("Description cannot be empty");
        }
        this.desc = desc;
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
        for (String timeFormat : timeFormats) {
            for (String dateFormat : dateFormats) {
                try {
                    StringBuilder fm = new StringBuilder(dateFormat).append(" ").append(timeFormat);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fm.toString());
                    LocalDateTime formattedDateTime = LocalDateTime.parse(time, formatter);
                    return formattedDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
                } catch (DateTimeParseException e) {
                    continue;
                }
            }
        }

        for (String fm : timeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fm);
                LocalTime formattedTime = LocalTime.parse(time, formatter);
                return formattedTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (String fm : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fm);
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
        return this.isDone.charAt(1) + " | " + this.desc;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return this.isDone + " " + this.desc;
    }
}
