package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 * An event task has a description, a start time, and an end time.
 */
public class Events extends Task {
    public LocalDateTime start;
    public LocalDateTime end;

    /**
     * Constructs an Events task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description + " (from: " + formatDate(start) + ", to: " + formatDate(end) + ")", TaskType.EVENT);
        this.start = start;
        this.end = end;
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
     * Returns the task type of this task.
     *
     * @return A string representing the task type, which is "E" for event tasks.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     *
     * @return A formatted string representing the task for file storage.
     */
    @Override
    public String toFileString() {
        int index = description.indexOf("(from:");
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description.substring(0, index).trim(), localDateTimeString(start), localDateTimeString(end));
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
}