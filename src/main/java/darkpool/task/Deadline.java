package darkpool.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import darkpool.DarkpoolException;



/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime byTime;

    /**
     * Constructs a Deadline task with the specified description, deadline time, and completion status.
     *
     * @param description The description of the task.
     * @param byTime The deadline time for the task in string format.
     * @param isDone The completion status of the task.
     * @throws DarkpoolException If the deadline time is not in the correct format.
     */
    public Deadline(String description, String byTime, boolean isDone) throws DarkpoolException {
        super(description, isDone);

        try {
            this.byTime = LocalDateTime.parse(byTime, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException("know what a date is?");
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + this.description + " (by:" + this.byTime.format(FORMATTER) + ")";
    }

    /**
     * Returns a string representation of the Deadline task formatted for file storage.
     *
     * @return A string representation of the Deadline task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return ("D | " + (isDone ? "1" : "0") + " | " + this.description + " | "
                + this.byTime.format(FORMATTER) + "\n");
    }

}
