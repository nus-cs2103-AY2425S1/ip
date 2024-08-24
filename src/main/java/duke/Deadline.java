package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the task management system.
 * A deadline task has a description and a date/time by which it needs to be completed.
 */
public class Deadline extends IndividualTask {

    private String returnBy;
    private LocalDateTime formatReturnBy;

    /**
     * Constructs a {@code Deadline} object with the specified task description and return by date/time.
     *
     * @param taskDescription The description of the task.
     * @param returnBy The date and time by which the task should be completed,
     * in the format "d/M/yyyy HHmm".
     */
    public Deadline(String taskDescription, String returnBy) {
        super(taskDescription);
        this.returnBy = returnBy;
        this.formatReturnBy = parseDateTime(returnBy);
    }

    /**
     * Parses the date/time string into a {@code LocalDateTime} object.
     *
     * @param date The date/time string to be parsed, in the format "d/M/yyyy HHmm".
     * @return The parsed {@code LocalDateTime} object, or {@code null} if parsing fails.
     */
    private LocalDateTime parseDateTime(String date) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(date, formatDate);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong format for date: " + date);
            return null;
        }
    }

    /**
     * Formats the {@code formatReturnBy} date/time into a human-readable string.
     *
     * @return The formatted date/time string in the format "MMM d yyyy, h:mm a".
     */
    public String formatDateTime() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return this.formatReturnBy.format(outputFormatter);
    }

    /**
     * Returns the string representation of the deadline task in a format suitable for saving to a file.
     *
     * @return The string representation of the task in the
     * format "D | statusIcon | taskDescription | returnBy".
     */
    @Override
    public String saveToFileFormat() {
        return "D | " +
                this.getSaveIcon() +
                " | " +
                this.getTaskDescription() +
                " | " + this.returnBy;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the task, including the type, status, and return by date/time.
     */
    @Override
    public String toString() {
        return "[D]" +
                super.toString() +
                " (by: " + this.formatDateTime() + ")";
    }
}
