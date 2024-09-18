package tasks;

/**
 * Represents an abstract task with a description, a note, and a done status.
 * This class serves as a base for different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected String note = "-";
    protected boolean isDone;

    /**
     * Constructs a Task with a description. The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with a description and an optional note. The task is initially marked as not done.
     *
     * @param description The description of the task.
     * @param note An optional note associated with the task.
     */
    public Task(String description, String note) {
        this.description = description;
        this.note = note;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing whether the task is done or not.
     *
     * @return A string "X" if the task is done, or a space if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the done status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets a string representation of the timings associated with the task.
     * This method must be implemented by subclasses.
     *
     * @return A string representing the timings of the task.
     */
    public abstract String getTimings();

    /**
     * Gets the symbol representing the type of task.
     * This method must be implemented by subclasses.
     *
     * @return A string symbol representing this task type.
     */
    public abstract String getSymbol();

    /**
     * Gets the note associated with the task.
     *
     * @return The note of the task.
     */
    public String getNote() {
        return this.note;
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
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
