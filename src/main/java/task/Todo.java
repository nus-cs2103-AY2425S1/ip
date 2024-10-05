package task;

import java.util.Collection;

/**
 * Represents a to-do task.
 * <p>
 * The {@code Todo} class extends {@link Task} to represent a task that needs to be done.
 * It provides methods to display the task and to retrieve a formatted string suitable for database storage.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} with the specified description, completion status, and tags.
     * <p>
     * Initializes the to-do task with the given description, completion status, and associated tags.
     * </p>
     *
     * @param description The description of the to-do task.
     * @param isDone      A boolean indicating whether the to-do task is completed.
     * @param tags        A collection of tags associated with the to-do task.
     */
    public Todo(String description, boolean isDone, Collection<String> tags) {
        super(description, isDone, tags);
    }

    /**
     * Constructs a {@code Todo} with the specified description and tags.
     * <p>
     * Initializes the to-do task with the given description, associated tags, and the task is initially not done.
     * </p>
     *
     * @param description The description of the to-do task.
     * @param tags        A collection of tags associated with the to-do task.
     */
    public Todo(String description, Collection<String> tags) {
        super(description, tags);
    }


    /**
     * Returns a string representation of the {@code Todo} task for display purposes.
     * <p>
     * The string format is "[T][task details] [tags]". The task details include the status icon and description.
     * A space is added between the task description and the tags.
     * </p>
     *
     * @return A string representation of the {@code Todo} task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + this.tags.toString();
    }


    /**
     * Returns a string representation of the {@code Todo} suitable for database storage.
     * <p>
     * The string format is "T | [isDone] | [description]". The completion status and description are included.
     * </p>
     *
     * @return A string representation of the {@code Todo} suitable for database storage.
     */
    @Override
    public String getDatabaseString() {
        return String.format("T | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.tags.getDatabaseString());
    }
}
