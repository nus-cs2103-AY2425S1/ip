package lict.task;

import java.time.DateTimeException;

import lict.DateTime;
import lict.LictException;
import lict.Ui;

/**
 * The {@code lict.task.Deadline} class represents a task that has a deadline.
 * It extends the {@code lict.task.Task} class and provides specific implementations for
 * the {@code toString} and {@code toData} methods.
 */
public class Deadline extends Task {
    private static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Invalid format for deadline. "
            + "Please ensure that deadline is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.";
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
            throw new LictException(INVALID_DEADLINE_FORMAT_MESSAGE);
        }
    }

    @Override
    public boolean isScheduledTask() {
        return true;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getString() + ")";
    }

    /**
     * Updates the deadline of the task based on the provided information.
     *
     * @param ui The {@code Ui} object for interaction.
     * @param info The new deadline in the format "/by {new deadline}".
     * @throws LictException If the format of the provided information is incorrect or the deadline format is invalid.
     */
    @Override
    public void snoozeTask(Ui ui, String info) throws LictException {
        if (!info.startsWith("/by")) {
            throw new LictException("Please include the new deadline you wish to set in the following format:\n"
                    + "snooze {task number} /by {new deadline}");
        }
        String newDeadline = info.substring(3).trim();
        try {
            this.by = new DateTime(newDeadline);
        } catch (DateTimeException e) {
            throw new LictException(INVALID_DEADLINE_FORMAT_MESSAGE);
        }
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
        String[] messageParts = dataMessage.split(PIPE_DELIMITER, 2);
        String description = messageParts[0].trim();
        if (description.isEmpty() || messageParts.length != 2) {
            throw new LictException();
        }
        return new Deadline(description, messageParts[1].trim());

    }
}
