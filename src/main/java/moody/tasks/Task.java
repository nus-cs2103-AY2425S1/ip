package moody.tasks;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents a task with a description and completion status.
 * The Task class provides methods to manage and display the task's status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    /**
     * Creates a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new LinkedHashSet<>();
    }

    /**
     * Returns the status icon of the Task.
     * The icon is "X" if the task is done, otherwise a blank space.
     *
     * @return The status icon of the Task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the Task as done.
     * Updates the task status to indicate completion.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the Task as not done.
     * Updates the task status to indicate that it is incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Adds a tag to the Task.
     * Tags are managed in a set to avoid duplicates.
     *
     * @param tag The tag to be added.
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Removes a tag from the Task.
     *
     * @param tag The tag to be removed.
     * @return True if the tag was removed successfully, false otherwise.
     */
    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    /**
     * Retrieves all tags associated with the Task.
     *
     * @return A set of tags associated with the Task.
     */
    public Set<String> getTags() {
        return tags;
    }

    /**
     * Converts the Task to a format suitable for saving to a file.
     * The format includes the completion status, description and tags.
     *
     * @return A string representation of the Task in file format.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns a string representation of the Task for display purposes.
     * The format includes the status icon, and description.
     *
     * @return A string representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(),
                this.description);
    }
}
