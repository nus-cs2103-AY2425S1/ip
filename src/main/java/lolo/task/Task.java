package lolo.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task in the Lolo application.
 * A task has a description and can be marked as done or not done.
 * This class is abstract and serves as the base class for specific types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private List<String> tags;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" represents a task that is done, while a blank space represents a task that is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public List<String> getTags() {
        return tags;
    }
    /**
     * Returns a string representation of the task.
     * The string includes the status icon and the task description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + getTagString();
    }

    /**
     * Returns a string representation of the task tags in a clean format.
     *
     * @return The string representation of tags.
     */
    public String getTagString() {
        if (tags.isEmpty()) {
            return " Tags: [ ]";
        }
        return " Tags: [" + String.join(", ", tags) + "]";
    }

    /**
     * Returns a string representation of the task formatted for saving to a file.
     *
     * @return The data string representation of the task.
     */
    public abstract String toDataString();
}
