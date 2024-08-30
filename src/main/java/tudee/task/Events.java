package tudee.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Events task.
 * A Events task is a task with a specific start date and end date.
 */
public class Events extends Task {
    /**
     * The start date for this task.
     */
    LocalDate start;
    /**
     * The end date for this task.
     */
    LocalDate end;
    /**
     * Constructs a new Events task with the specified task description, start date and end date.
     *
     * @param taskString The task description.
     * @param start The start date for the task, in the format yyyy-MM-dd.
     * @param end The end date for the task, in the format yyyy-MM-dd.
     */
    public Events(String taskString, String start, String end) {
        super(taskString);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Returns the start date of this task.
     *
     * @return The start date as a LocalDate object.
     */
    public LocalDate getStart() {
        return this.start;
    }

    /**
     * Returns the end date of this task.
     *
     * @return The end date as a LocalDate object.
     */
    public LocalDate getEnd() {
        return this.end;
    }

    /**
     * Converts the Events task into a formatted string for saving to a file.
     *
     * @return A formatted string for the Events task.
     */
    @Override
    public String toFileString() {
        return "E | " + (done ? 1 : 0) +" | " + taskString + " | " + start + " | " + end;
    }

    /**
     * Returns a string representation of the events task consisting
     * its type, completion status, start date and end date.
     *
     * @return The string representation of the events task.
     */
    @Override
    public String toString() {
        String convertedStart = start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String convertedEnd = end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + "(from: " + convertedStart + " to: " + convertedEnd + ")";
    }
}