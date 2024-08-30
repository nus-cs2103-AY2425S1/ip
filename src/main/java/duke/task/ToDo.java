package duke.task;

/**
 * Represents a to-do task with a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the formatted string representation of the to-do task to be saved in storage.
     *
     * @return A formatted string for saving the to-do task to a file.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}