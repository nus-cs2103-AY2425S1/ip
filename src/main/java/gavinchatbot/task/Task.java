package gavinchatbot.task;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
    }

    /**
     * Returns the status icon of the task.
     * "X" if the task is done, otherwise a blank space.
     *
     * @return The status icon of the task.
     */
    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        assert isDone : "Task should already be marked as done";
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
        assert !isDone : "Task should already be marked as not done";
    }

    /**
     * Returns the string representation of the task in the format used for saving to a file.
     *
     * @return The string representation of the task in save format.
     */
    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag The tag to be added.
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Returns the string representation of the task, including its status, description, and tags.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String tagString = tags.isEmpty() ? "" : " Tags: " + tags;
        return "[" + getStatusIcon() + "] " + description + tagString;
    }


    /**
     * Returns whether the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

}
