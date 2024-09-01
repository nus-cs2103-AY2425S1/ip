package bob.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bob.Parser;

/**
 * Represents a Deadline task.
 * <p>
 * A Deadline task is a type of task that has a description, a completion status, and a due date.
 */
public class Deadline extends Task {
    private static final String SYMBOL = "D";
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with a given description, completion status and due date.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param by          The due date of the task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with a given description and due date.
     * The completion status is initialised as false.
     *
     * @param description The description of the task.
     * @param by          The due date of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        this(description, false, by);
    }

    /**
     * Returns the symbol indicating Deadline task.
     *
     * @return The symbol "D".
     */
    public String getSymbol() {
        return SYMBOL;
    }

    /**
     * Returns the string representation of the Deadline task with the format for storage.
     *
     * @return A string representation of the Deadline task to be saved.
     */
    public String getTaskLine() {
        return getSymbol() +  "," + isDoneBinary() + "," + description + "," + Parser.getDateTimeStr(by);
    }

    /**
     * Checks if the Deadline task occurs on a specific date.
     *
     * @param date The specific date to check relevance against.
     * @return true if the task occurs on the specified date, false otherwise.
     */
    public boolean isRelevant(LocalDate date) {
        LocalDate byDate = by.toLocalDate();
        return byDate.equals(date);
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes the task type symbol, status icon, task description and due date.
     *
     * @return A formatted string of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[" + getSymbol() + "]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
