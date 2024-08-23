package citadel.Task;

/**
 * Represents a to-do task in the Citadel application.
 * A to-do task only has a description and does not have a specific time or date associated with it.
 * This class extends {@link Task}.
 */
public class ToDo extends Task {

    /**
     * Constructs a new to-do task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its type and status icon.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the to-do task for printing or saving,
     * including its type and status icon.
     *
     * @return A string representation of the to-do task for printing or saving.
     */
    @Override
    public String printTask() {
        return "[T]" + super.toString();
    }
}
