package bob.task;

import java.time.LocalDate;

/**
 * Represents a ToDo task.
 * <p>
 * A ToDo task is a type of task that only has a description and a completion status,
 * without any time constraints.
 */
public class ToDo extends Task {
    private static final String SYMBOL = "T";

    /**
     * Constructs a new ToDo task with a given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructs a new ToDo task with a given description.
     *The completion status is initialised as false.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Returns the symbol indicating ToDo task.
     *
     * @return The symbol "T".
     */
    public String getSymbol() {
        return SYMBOL;
    }

    /**
     * Returns the string representation of the ToDo task with the format for storage.
     *
     * @return A string representation of the ToDo task to be saved.
     */
    public String getTaskLine() {
        return getSymbol() +  "," + isDoneBinary() + "," + description;
    }

    /**
     * Checks if the ToDo task occurs on a specific date.
     *
     * @param date The date to check relevance against.
     * @return false since ToDo tasks have no specific dates.
     */
    public boolean isRelevant(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the ToDo task.
     * Includes the task type symbol, status icon and the task description.
     *
     * @return A formatted string of the ToDo task.
     */
    @Override
    public String toString() {
        return "[" + getSymbol() + "]" + super.toString();
    }
}
