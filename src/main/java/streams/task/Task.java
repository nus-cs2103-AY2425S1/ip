package streams.task;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an abstract task in the task list.
 * This class provides common functionality for all types of tasks.
 */
public abstract class Task {
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    protected String description;
    protected boolean isCompleted;
    protected Set<String> tags;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != "" : "Task description should not be null";
        this.description = description;
        this.isCompleted = false;
        this.tags = new HashSet<>();
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isCompleted = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isCompleted = false;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag The tag to be added.
     */
    public void addTag(String tag) {
        tags.add(tag.toLowerCase());
    }

    /**
     * Removes a tag from the task.
     *
     * @param tag The tag to be removed.
     */
    public void removeTag(String tag) {
        tags.remove(tag.toLowerCase());
    }

    /**
     * Checks if the task has a specific tag.
     *
     * @param tag The tag to check for.
     * @return true if the task has the tag, false otherwise.
     */
    public boolean hasTag(String tag) {
        return tags.contains(tag.toLowerCase());
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task, including its status, description, and tags.
     */
    @Override
    public String toString() {
        return (isCompleted ? "[✓] " : "[✗] ") + description + " " + tags;
    }
}

