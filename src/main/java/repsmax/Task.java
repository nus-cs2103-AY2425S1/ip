package repsmax;

/**
 * Represents a task with a description and completion status.
 * <p>
 * The {@code Task} class stores information about a task, including its
 * description and whether it is marked as done. It provides methods to
 * manipulate and retrieve the status of the task, as well as to convert
 * the task to and from a file format.
 * </p>
 */
public class Task {
    private final String description;
    private boolean isDone;
    private int priority;

    /**
     * Constructs a {@code Task} with the specified description. The task
     * is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description, int priority) {
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Returns the status icon representing the completion status of the task.
     * <p>
     * If the task is done, returns "X". Otherwise, returns a blank space.
     * </p>
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    public String getPriority() {
        switch (priority) {
            case 1:
                return "[High Priority]";
            case 2:
                return "[Medium Priority]";
            case 3:
                return "[Low Priority]";
            default:
                return "[No Priority]";
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task in a user-friendly format.
     * <p>
     * The format is "[X] description" if the task is done, or "[ ] description"
     * if the task is not done.
     * </p>
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getPriority() + " " + description;
    }

    /**
     * Converts the task to a format suitable for saving to a file.
     * <p>
     * The format is "T | doneStatus | description", where "doneStatus" is
     * "1" if the task is done and "0" if it is not.
     * </p>
     *
     * @return A string representation of the task in file format.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + priority + " | " + description;
    }

    /**
     * Creates a {@code Task} object from a string in file format.
     * <p>
     * The expected format is "T | doneStatus | description". The method
     * splits the input string and creates a task based on the parsed data.
     * </p>
     *
     * @param fileFormat The string representation of the task in file format.
     * @return A {@code Task} object created from the file format string.
     * @throws IllegalArgumentException If the file format string is invalid.
     */
    public static Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\| ");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid task format.");
        }
        String description = parts[3];
        boolean isDone = parts[1].equals("1");
        int priority = Integer.parseInt(parts[2]);
        Task task = new Task(description, priority);
        if (isDone) {
            task.setDone();
        }
        return task;
    }
}
