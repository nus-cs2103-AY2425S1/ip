package opus;

/**
 * Represents a generic task in the application. Each task has a description and
 * a status indicating whether it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
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
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns whether the task is done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string format for saving the task to a file.
     * This method must be implemented by subclasses.
     *
     * @return The formatted string for saving the task.
     */
    public abstract String toSaveFormat();

    /**
     * Creates a Task object from a formatted string read from storage.
     *
     * @param fullLine The line read from the storage file.
     * @return A new Task object based on the parsed string, or {@code null} if the task type is unknown.
     */
    public static Task fromFileFormat(String fullLine) {
        String[] parts = fullLine.split("\\|");

        switch (parts[0]) {
            case "T":
                return ToDo.fromFileFormat(fullLine);
            case "D":
                return Deadline.fromFileFormat(fullLine);
            case "E":
                return Event.fromFileFormat(fullLine);
            default:
                return null;
        }
    }
}
