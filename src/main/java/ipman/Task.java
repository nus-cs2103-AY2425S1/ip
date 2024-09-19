package ipman;

import java.util.ArrayList;

/**
 * Represents a task with a description and completion status.
 * Provides methods to mark and unmark the task as done.
 * This class serves as a base class for specific types of tasks.
 *
 * @author miloaisdino
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags;

    /**
     * Constructs a Task with the specified description.
     * The task is initially not done.
     *
     * @param description Name of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the task to not done
     * @return The new string representation of the task
     */
    public String unmarkStatus() {
        this.isDone = false;
        return this.toString();
    }

    /**
     * Sets the task to done
     * @return The new string representation of the task
     */
    public String markStatus() {
        this.isDone = true;
        return this.toString();
    }

    /* Adds a tag to the task
    *
    * @param tag The tag to be added
    */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /* Converts the tags to a string
    *
    * @return The string representation of the tags
    */
    public String tagsToString() {
        if (tags.isEmpty()) {
            return "";
        }
        return tags.toString();
    }

    /* Removes a tag from the task
     *
     * @param tag The tag to be removed
     */
    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + " " + this.tagsToString();
    }
}
