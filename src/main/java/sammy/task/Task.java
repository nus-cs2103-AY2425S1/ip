package sammy.task;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a task with a description and a completion status.
 * This is an abstract class that serves as a base for different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    /**
     * Constructs a Task object with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        if (this.tags == null) {
            this.tags = new HashSet<>();  // Initialize only if null
        }    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    // Remove a tag from the task
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    // Get all tags for the task
    public Set<String> getTags() {
        return tags;
    }

    /**
     * Returns the status icon of the task, which is "X" if the task is done or " " if not.
     *
     * @return A string representing the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting its status to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + " Tags: " + tags;
    }
}

