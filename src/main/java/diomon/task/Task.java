package diomon.task;

/**
 * The {@code Task} class represents a generic task with a description and completion status.
 * It serves as a base class for more specific tasks like {@link Todo}, {@link Event}, and {@link Deadline}.
 */
public class Task {
    String description;
    public static final String COMPLETEICON = "X";
    public static final String INCOMPLETEICON = " ";
    public static final String TYPEICON = " ";
    boolean completed;

    /**
     * Constructs a {@code Task} with the given description. By default, the task is not completed.
     *
     * @param task The description of the task.
     */
    public Task(String task){
        this.description = task;
        this.completed = false;
    }

    /**
     * Constructs a {@code Task} with the given completion status and description.
     *
     * @param complete     Whether the task is completed or not.
     * @param description  The description of the task.
     */
    public Task(boolean complete, String description) {
        this.completed = complete;
        this.description = description;

    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.completed = false;
    }

    /**
     * Gets the status icon of the task, which is "X" if completed and a blank space otherwise.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return this.completed ? COMPLETEICON : INCOMPLETEICON;
    }

    /**
     * Gets the type icon of the task. This can be overridden by subclasses to provide specific task types.
     *
     * @return The type icon of the task (defaults to a blank space).
     */
    public String getTypeIcon() {
        return TYPEICON;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Converts the task into a format suitable for storage in a file.
     *
     * @return A string formatted for storage, representing the type, status, and description of the task.
     */
    public String toStorageString(){
        return String.format("%s|%s|%s", TYPEICON, getStatusIcon(),this.description);
    }

    /**
     * Compares the current task with another object for equality. Two tasks are considered equal if
     * their descriptions and completion statuses are the same.
     *
     * @param other The object to compare with.
     * @return {@code true} if the tasks are equal, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Task temp) {
            return temp.completed == completed && temp.description.equals(description);
        }
        return false;
    }
}
