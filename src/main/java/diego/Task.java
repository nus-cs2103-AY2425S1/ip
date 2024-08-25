package diego;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
<<<<<<< HEAD
     * Unmarks the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        String check = isDone ? "[X]" : "[ ]";
        return " " + check + " " + this.description;
    }

    /**
     * Returns the file format string of the task for storage.
     *
     * @return A string suitable for storing in a file.
     */
=======
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

>>>>>>> origin/branch-Level-9
    public abstract String toFileFormat();
}
