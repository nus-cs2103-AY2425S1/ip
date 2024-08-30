package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 * A deadline task has a description and a deadline date/time by which the task must be completed.
 */
public class Deadlines extends Task {
    public LocalDateTime deadline;

    /**
     * Constructs a Deadlines task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline    The date and time by which the task should be completed.
     */
    public Deadlines(String description, LocalDateTime deadline) {
        super(description + " (by: " + formatDate(deadline) + ")", TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Returns the task type of this task.
     *
     * @return A string representing the task type, which is "D" for deadline tasks.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Formats a LocalDateTime object into a string in the format "MMM dd yyyy HHmm".
     *
     * @param date The LocalDateTime object to be formatted.
     * @return A formatted date string.
     */
    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    /**
     * Formats a LocalDateTime object into a string in the format "d/M/yyyy HHmm".
     *
     * @param date The LocalDateTime object to be formatted.
     * @return A formatted date string.
     */
    private static String localDateTimeString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     *
     * @return A formatted string representing the task for file storage.
     */
    @Override
    public String toFileString() {
        int index = description.indexOf("(by:");
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description.substring(0, index).trim(), localDateTimeString(deadline));
    }
}