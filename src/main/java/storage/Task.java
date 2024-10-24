package storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private final List<Tag> tags;

    /**
     * Creates a new Task.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tags = new ArrayList<>();
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        if (tags.isEmpty()) {
            return "[" + getStatusIcon() + "] " + description;
        }
        return "[" + getStatusIcon() + "] " + description + tags;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag The tag to add.
     */
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    /**
     * Removes a tag from the task.
     *
     * @param tag The tag to remove.
     */
    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    /**
     * Returns the task in the file format.
     *
     * @return The task in the file format.
     */
    public abstract String toFileFormat();

    /**
     * Returns the tags of the task in String format.
     *
     * @return The tags of the task.
     */
    public String getTags() {
        if (tags.isEmpty()) {
            return "";
        }
        StringBuilder tagsString = new StringBuilder();
        for (Tag tag : tags) {
            tagsString.append(tag.getName()).append(",");
        }
        tagsString.setLength(tagsString.length() - 1); // Remove trailing comma
        return tagsString.toString();
    }

    /**
     * Returns if the tag exists in the task.
     * @param tag The tag to check.
     * @return True if the tag exists in the task, false otherwise.
     */
    public boolean tagExists(Tag tag) {
        return tags.contains(tag);
    }
}
