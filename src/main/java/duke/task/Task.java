package duke.task;
import java.time.LocalDateTime;

/**
 * Represents an abstract task with a description and completion status.
 * This class serves as a base class for different types of tasks such as ToDos, Deadlines, and Events.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task. True if the task is done, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves the description of this task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task, indicating whether it is completed or not.
     *
     * @return A string representing the task's status icon, "[X]" if done, "[ ]" if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     * This method must be implemented by subclasses to specify how to save the task's details.
     *
     * @return A string representing the task in a format suitable for file storage.
     */
    public abstract String toFileString();

    /**
     * Returns a string representation of the task for display purposes.
     * This includes the status icon and the description of the task.
     *
     * @return A string representing the task in the format "[status] description".
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns the date associated with the task.
     *
     * <p>For tasks that have a specific date associated with them, such as
     * {@code Deadline} and {@code Event} tasks, this method will return
     * the relevant date. For tasks without a specific date, such as
     * {@code ToDo} tasks, this method will return {@code null}.</p>
     *
     * @return the {@code LocalDateTime} of the task, or {@code null} if
     *         the task does not have a date associated with it.
     */
    public LocalDateTime getDate() {
        return null;
    }
}

