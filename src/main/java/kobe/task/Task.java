package kobe.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a general task in the Duke chatbot application.
 * A task has a name and can be marked as done or not done.
 */
public class Task {
    public final String name;
    public boolean isDone;
    private List<String> tags;

    /**
     * Constructs a Task with the specified name and sets the task as not done by default.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false; // Default is not done
        this.tags = new ArrayList<>();
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
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag The tag to be added.
     */
    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Gets the list of tags for the task.
     *
     * @return The list of tags.
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Checks if the task contains a specific tag.
     *
     * @param tag The tag to check for.
     * @return true if the tag exists, false otherwise.
     */
    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * @return A formatted string representing the task in a file-friendly format.
     */
    public String toFileFormat() {
        return (this instanceof Todo ? "T" : (this instanceof Deadline ? "D" : "E"))
                + " | " + (isDone ? "1" : "0")
                + " | " + name
                + " | " + String.join(",", tags); // Include tags in the file format
    }

    /**
     * Returns the string representation of the task, including its completion status and tags.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        String tagsString = tags.isEmpty() ? "" : " #" + String.join(" #", tags);
        return showDoneOrNot() + " " + name + tagsString;
    }

    /**
     * Returns a string representation indicating whether the task is done or not.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String showDoneOrNot() {
        return (isDone ? "[X]" : "[ ]"); // Return X or space depending on isDone
    }
}
