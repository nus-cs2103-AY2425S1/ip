package blue.task;

/**
 * An abstract class representing a general task.
 * Specific types of tasks (e.g., ToDoTask, DeadlineTask, EventTask) should extend this class.
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;

    /** The status of the task, whether it is done or not. */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a status icon representing whether the task is done.
     *
     * @return "1" if the task is done, "0" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns an icon representing the status of the task.
     *
     * @return "X" if the task is done, a space otherwise.
     */
    public String getIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getIcon() + "] " + this.description;
    }

    /**
     * Returns the string representation of the task formatted for saving to a file.
     *
     * @return The string formatted for saving to a file.
     */
    public abstract String toFileString();

    /**
     * Creates a Task object from a string representation read from a file.
     *
     * @param fileString The string representation of the task from the file.
     * @return The corresponding Task object.
     * @throws IllegalArgumentException If the task type in the file is invalid.
     */
    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new ToDoTask(description);
            break;
        case "D":
            // Handle missing date
            String by = parts.length > 3 ? parts[3] : "";
            // Pass the date string directly
            task = new DeadlineTask(description, by);
            break;
        case "E":
            // Handle missing from
            String from = parts.length > 3 ? parts[3] : "";
            // Handle missing to
            String to = parts.length > 4 ? parts[4] : "";
            task = new EventTask(description, from, to);
            break;
        default:
            throw new IllegalArgumentException("Invalid task type in file: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}
