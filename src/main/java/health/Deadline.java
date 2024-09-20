package health;

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
     * Constructs a {@code Deadline} object with the specified task description and due date/time.
     *
     * @param taskDescription The description of the task.
     * @param returnBy The date and time by which the task should be completed, in the format "d/M/yyyy HHmm".
     * @throws MentalHealthException If the provided date/time string cannot be parsed.
     */
    public Deadline(String taskDescription, String returnBy) throws MentalHealthException {
        super(taskDescription);
        this.returnBy = returnBy;
        this.formatReturnBy = parseDateTime(returnBy);
    }

    /**
     * Sets a new due date/time for this task.
     *
     * @param returnBy The new date and time by which the task should be completed, in the format "d/M/yyyy HHmm".
     * @throws MentalHealthException If the provided date/time string cannot be parsed.
     */
    public void setReturnBy(String returnBy) throws MentalHealthException {
        this.returnBy = returnBy;
        this.formatReturnBy = parseDateTime(this.returnBy);
    }

    /**
     * Parses the date/time string into a {@code LocalDateTime} object.
     *
     * @param date The date/time string to be parsed, in the format "d/M/yyyy HHmm".
     * @return The parsed {@code LocalDateTime} object.
     * @throws MentalHealthException If the date/time string cannot be parsed.
     */
    private LocalDateTime parseDateTime(String date) throws MentalHealthException {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(date, formatDate);
        } catch (DateTimeParseException e) {
            throw new MentalHealthException(e.getMessage());
        }
    }

    /**
     * Formats the due date/time into a human-readable string.
     *
     * @return The formatted date/time string, in the format "MMM d yyyy, h:mm a".
     * @throws MentalHealthException If the date/time formatting fails.
     */
    public String formatDateTime() throws MentalHealthException {
        try {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
            return this.formatReturnBy.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new MentalHealthException(e.getMessage());
        }
    }

    /**
     * Returns the string representation of the deadline task in a format suitable for saving to a file.
     *
     * @return The string representation of the task, in the format "D | statusIcon | taskDescription | returnBy".
     */
    @Override
    public String saveToFileFormat() {
        return "D | "
                + this.getSaveIcon()
                + " | "
                + this.getTaskDescription()
                + " | " + this.returnBy;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the task, including its type, status, and due date/time.
     */
    @Override
    public String toString() {
        try {
            return "[D]"
                    + super.toString()
                    + " (by: " + this.formatDateTime() + ")";
        } catch (MentalHealthException e) {
            return e.getMessage();
        }
    }
}
