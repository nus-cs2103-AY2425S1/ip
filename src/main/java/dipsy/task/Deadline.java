package dipsy.task;

import java.time.LocalDate;

import dipsy.parser.DateParser;

/**
 * Represents a task with a deadline. A Deadline task has a description and a specific due date by which it
 * needs to be completed.
 * This class extends the base {@link Task} class and adds functionality specific to handling deadlines.
 */
public class Deadline extends Task {

    /** The due date (deadline) for the task. */
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date (deadline) of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new {@code Deadline} task with the specified description, completion status, and due date.
     *
     * @param description The description of the deadline task.
     * @param isDone The completion status of the task (true if the task is completed, false otherwise).
     * @param by The date by which the task needs to be completed.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the type of the task, which is "Deadline" for this class.
     * This method is used to identify the specific type of task.
     *
     * @return The string "Deadline" indicating the type of task.
     */
    @Override
    protected String getTaskType() {
        return "Deadline";
    }

    /**
     * Returns the relevant date for this task, which is the deadline date.
     * This method is used to retrieve the date associated with the deadline.
     *
     * @return The {@link LocalDate} object representing the deadline for the task.
     */
    @Override
    public LocalDate getRelevantDate() {
        return this.by;
    }

    /**
     * Formats the Deadline task into a CSV-compatible string for storage, including the deadline date.
     * This format is used to save the task information to a file.
     *
     * @return A string representing the Deadline task in CSV format.
     */
    @Override
    public String formatToCsv() {
        String res = super.formatToCsv();
        res += DELIMITER + wrapInQuotes(DateParser.formatDateForStorage(by));
        return res;
    }

    /**
     * Returns a string representation of the Deadline task, including its description and deadline date.
     * This method is used to display the task in a human-readable format, showing its type, description, and due date.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.formatDateForDisplay(by) + ")";
    }
}
