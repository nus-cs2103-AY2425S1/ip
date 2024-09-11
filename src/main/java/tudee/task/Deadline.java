package tudee.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tudee.TudeeException;

/**
 * Represents a Deadline task.
 * A Deadline task is a task with a specific deadline.
 */
public class Deadline extends Task {
    private static final int MARKED_VALUE = 1;
    private static final int UNMARKED_VALUE = 0;

    /**
     * The deadline date for this task.
     */
    private final LocalDate deadline;

    /**
     * Constructs a new Deadline task with the specified task description and deadline date.
     *
     * @param taskString The task description.
     * @param deadline The deadline date for the task, in the format yyyy-MM-dd.
     */
    public Deadline(String taskString, String deadline) throws TudeeException {
        super(taskString);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new TudeeException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    /**
     * Returns the deadline date of this task.
     *
     * @return The deadline date as a LocalDate object.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Converts the Deadline task into a formatted string for saving to a file.
     *
     * @return A formatted string for the Deadline task.
     */
    @Override
    public String toFileString() {
        return "D | " + (done ? MARKED_VALUE : UNMARKED_VALUE) + " | " + taskString + " | " + deadline;
    }

    /**
     * Returns a string representation of the deadline task consisting its type, completion status and deadline.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        String convertedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + convertedDeadline + ")";
    }
}
