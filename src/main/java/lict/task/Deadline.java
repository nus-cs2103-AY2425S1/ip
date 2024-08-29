package lict.task;

import lict.DateTime;

import lict.LictException;

import java.time.DateTimeException;

/**
 * The {@code lict.task.Deadline} class represents a task that has a deadline.
 * It extends the {@code lict.task.Task} class and provides specific implementations for the {@code toString} and {@code toData} methods.
 */
public class Deadline extends Task {
    protected DateTime by;

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline by which the task needs to be completed, in the format 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.
     * @throws LictException If the deadline format is invalid.
     */
    public Deadline(String description, String by) throws LictException {
        super(description);
        try {
            this.by = new DateTime(by);
        } catch (DateTimeException e) {
            throw new LictException("Invalid format for deadline. Please ensure that deadline is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getString() + ")";
    }

    @Override
    public String toData() {
        String status = this.isDone ? "1" : "0";
        return String.format("DEADLINE | %s | %s | %s\n", status, this.description, by.getData());
    }

    /**
     * Loads a {@code Deadline} task from a data string.
     *
     * @param dataMessage The string containing the task data.
     * @return The {@code Deadline} object created from the data string.
     * @throws LictException If the data string is invalid or the deadline format is incorrect.
     */
    public static Deadline loadTask(String dataMessage) throws LictException {
        String[] messageParts = dataMessage.split("\\|", 2);
        String description = messageParts[0].trim();
        if (description.isEmpty() || messageParts.length != 2) {
            throw new LictException();
        }
        return new Deadline(description, messageParts[1].trim());

    }
}
