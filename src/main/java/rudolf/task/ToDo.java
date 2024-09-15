package rudolf.task;

/**
 * Represents a ToDo task.
 * A ToDo is a type of Task that signifies an item to be done.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone The completion status of the ToDo task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructs a ToDo task with the specified description and sets it as not done.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Returns a string representation of the ToDo task suitable for saving to a file.
     * The format is: "T | <status> | <description>".
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the ToDo task for display purposes.
     * The format is: "[T] <status> <description>".
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
