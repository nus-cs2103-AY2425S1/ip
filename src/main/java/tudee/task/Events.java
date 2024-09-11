package tudee.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tudee.TudeeException;

/**
 * Represents a Events task.
 * A Events task is a task with a specific start date and end date.
 */
public class Events extends Task {
    private static final int MARKED_VALUE = 1;
    private static final int UNMARKED_VALUE = 0;

    /**
     * The start date for this task.
     */
    private final LocalDate start;
    /**
     * The end date for this task.
     */
    private final LocalDate end;
    /**
     * Constructs a new Events task with the specified task description, start date and end date.
     *
     * @param taskString The task description.
     * @param start The start date for the task, in the format yyyy-MM-dd.
     * @param end The end date for the task, in the format yyyy-MM-dd.
     */
    public Events(String taskString, String start, String end) throws TudeeException {
        super(taskString);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new TudeeException("Invalid date format. Please use yyyy-MM-dd.");
        }
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
        return "E | " + (done ? MARKED_VALUE : UNMARKED_VALUE) + " | " + taskString + " | " + start + " | " + end;
    }

    /**
     * Returns a string representation of the events task with its type, completion status, start date and end date.
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
