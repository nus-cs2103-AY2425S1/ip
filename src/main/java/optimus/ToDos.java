package optimus;

/**
 * Represents a to-do task in the Optimus application.
 * A {@code ToDos} task is a specific type of {@code Task} that does not have any associated date or time.
 */
public class ToDos extends Task {

    /**
     * Constructs a new {@code ToDos} task with the specified description.
     *
     * @param description the description of the to-do task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its status and description.
     *
     * @return the string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string for saving the to-do task's data to a file.
     * The string includes the task type, completion status, and description.
     *
     * @return the formatted string for saving the to-do task.
     */
    @Override
    public String toSaveString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
