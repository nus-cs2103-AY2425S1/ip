package neko.task;

import java.time.format.DateTimeFormatter;

/**
 * The Task class represents a general task that can be either
 * a Todo, Deadline, or Event.
 * It provides functionalities to mark the task as done or not done,
 * retrieve the task's status, and get the task's description.
 * This class serves as a base class for more specific task types.
 *
 * <p>Each task has a name or description, a status indicating
 * whether it is done or not, and a date formatter for formatting date-related information if applicable.</p>
 *
 * <p>Subclasses can extend this class to implement more specific
 * behaviors for different task types.</p>
 *
 * @author  Siow Rui Ming
 * @version 0.1
 * @since   2024-08-19
 */

public class Task {
    private String name;
    private boolean isDone;
    protected static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("eee, d MMM uuuu h:mma");

    /**
     * Constructs a new Task object with the specified name of the task.
     * The task is initialized as not done by default.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done if it is not already marked as done.
     *
     * @return true if the task was successfully marked as done,
     *         false if the task was already marked as done.
     */
    public boolean markAsDone() {
        if (this.isDone) return false;
        this.isDone = true;
        return true;
    }

    /**
     * Marks the task as not done if it is already marked as done.
     *
     * @return true if the task was successfully marked as not done,
     *         false if the task was already marked as not done.
     */
    public boolean markAsNotDone() {
        if (!this.isDone) return false;
        this.isDone = false;
        return true;
    }

    /**
     * Gets the string representation of the status of the task,
     *
     * @return "X" if the task is marked as done,
     *         " " if the task is marked as not done.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Gets the string representation of the task's time, if applicable.
     *
     * <p>This method returns an empty string as a general Task does not have a specific time.
     * Subclasses that have time-related information should override this method.</p>
     *
     * @return an empty string, as a general Task does not have a time.
     */
    public String getTime() { return ""; };

    /**
     * Gets the name or description of the task.
     *
     * @return the name or description of the task.
     */
    public String getDescription() { return this.name; }

    /**
     * Gets the status of the task.
     *
     * @return true if the task is marked as done,
     *         false if the task is marked as not done.
     */
    public boolean isDone() { return isDone; }

    /**
     * Returns the string representation of the Task.
     *
     * <p>The string representation includes the status icon and the task's name or description.</p>
     *
     * @return the string representation of the Task, including its status and name.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + name;
    }
}
