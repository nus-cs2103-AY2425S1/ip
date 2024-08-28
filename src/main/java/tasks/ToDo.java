package tasks;

/**
 * Represents a to-do task.
 * A to-do task is a basic type of task with a description and a status indicator.
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do task with the specified description.
     *
     * @param description the description of the to-do task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task for display purposes.
     *
     * @return the string representation of the to-do task
     */
    @Override
    public String toString() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }

    /**
     * Returns the string representation of the to-do task suitable for saving to a file.
     *
     * @return the string representation of the to-do task for file storage
     */
    @Override
    public String toFileString() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }


}
