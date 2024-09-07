package yapper.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.function.Function;

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
     * @param time the string to be formatted
     * @return the formatted date/time string, or the original string if parsing fails
     */
    public String formattedDate(String time) {
        assert time != null : "Time should not be null";
        ArrayList<Function<String, String>> formatters = new ArrayList<>();
        formatters.add(this::formatStringToDateAndTime);
        formatters.add(this::formatStringToDate);
        formatters.add(this::formatStringToTime);

        for (Function<String, String> formatter : formatters) {
            try {
                return formatter.apply(time);
            } catch (YapperException e) {
                continue;
            }
        }

        return time;
    }

    /**
     * Formats a string into date/time readable format if it adheres to the given format
     *
     * @param time the string to be formatted into date and time format if possible
     * @return the formatted date and time as a string
     * @throws YapperException if unable to parse it into date and time format
     */
    public String formatStringToDateAndTime(String time) throws YapperException {
        for (String dateFormat : dateFormats) {
            for (String timeFormat : timeFormats) {
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
        throw new YapperException("Unable to parse date and time: " + time);
    }

    /**
     * Formats a string into a date if it adheres to the given format
     *
     * @param time the string to be formatted into date format if possible
     * @return the formatted date as a string
     * @throws YapperException if unable to parse it into date format
     */
    public String formatStringToDate(String time) {
        for (String dateFormat : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                LocalDate formattedDate = LocalDate.parse(time, formatter);
                return formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new YapperException("Unable to parse date: " + time);
    }

    /**
     * Formats a string into a time if it adheres to the given format
     *
     * @param time the string to be formatted into date format if possible
     * @return the formatted time as a string
     * @throws YapperException if unable to parse it into time format
     */
    public String formatStringToTime(String time) {
        for (String timeFormat : timeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
                LocalTime formattedTime = LocalTime.parse(time, formatter);
                return formattedTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new YapperException("Unable to parse time: " + time);
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
