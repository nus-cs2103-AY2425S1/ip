package barney.data.task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a task.
 *
 * This class encapsulates the properties and behaviors of a task. Each task has
 * a description and a status indicating whether it is marked as done or not.
 */
public class Task {

    private final String description;
    private boolean isMarked;

    /**
     * Constructor for Task.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task. Returns "X" if the task is marked as
     *         done, otherwise returns a space character.
     */
    public String getStatusIcon() {
        return (isMarked ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     *
     * @return void
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the Task object into an ArrayList of strings for saving purposes.
     * The ArrayList contains two elements: the first element represents the marking
     * status of the task, where "1" indicates the task is marked and "0" indicates
     * it is not marked. The second element represents the description of the task.
     *
     * @return An ArrayList of strings representing the Task object.
     */
    public ArrayList<String> toSaveArray() {
        return new ArrayList<>(Arrays.asList((this.isMarked ? "1" : "0"), this.description));
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return A string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
