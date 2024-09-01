package bob.task;

import bob.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task.
 * <p>
 * An Event task is a type of task that has a description, a completion status,
 * a start date and an end date.
 */
public class Event extends Task {
    private static final String SYMBOL = "E";
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Deadline task with a given description, completion status,
     * start date and end date.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param from        The start date of the task.
     * @param to          The end date of the task.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Deadline task with a given description, start date and end date.
     * The completion status is initialised as false.
     *
     * @param description The description of the task.
     * @param from        The start date of the task.
     * @param to          The end date of the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, false, from, to);
    }

    /**
     * Returns the symbol indicating Event task.
     *
     * @return The symbol "E".
     */
    public String getSymbol() {
        return SYMBOL;
    }

    /**
     * Returns the string representation of the Event task with the format for storage.
     *
     * @return A string representation of the Event task to be saved.
     */
    public String getTaskLine() {
        return getSymbol() +  "," + isDoneBinary() + "," + description + ","
                + Parser.getDateTimeStr(from) + "," + Parser.getDateTimeStr(to);
    }

    /**
     * Checks if the Event task occurs on a specific date.
     *
     * @param date The specific date to check relevance against.
     * @return true if the task occurs on the specified date, false otherwise.
     */
    public boolean isRelevant(LocalDate date) {
        LocalDate fromDate = from.toLocalDate();
        LocalDate toDate = to.toLocalDate();
        return !(date.isBefore(fromDate) || date.isAfter(toDate));
    }

    /**
     * Returns a string representation of the Event task.
     * Includes the task type symbol, status icon, task description, start date and end date.
     *
     * @return A formatted string of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[" + getSymbol() + "]" + super.toString() + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }
}
