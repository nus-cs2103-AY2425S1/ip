package task;

/**
 * Represents a to-do task.
 * Inherits from the Task class and provides specific implementations for to-do tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Retrieves the description of the to-do task.
     *
     * @return The description of the task.
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Provides a string representation of the to-do task.
     *
     * @return A string that represents the to-do task in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the to-do task to a format suitable for saving.
     *
     * @return A string representing the to-do task in a save format: "task.ToDo | status | description".
     */
    @Override
    public String toSaveFormat() {
        return "task.ToDo | " + (isDone ? "Done" : "Not Done")
                + " | " + description;
    }
}