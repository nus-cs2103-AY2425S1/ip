package deez;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class representing a task.
 */
public abstract class Task implements Serializable {
    protected static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("MMM dd yyyy " + "hh:mma");
    protected boolean done;
    protected String description;

    /**
     * Constructor for a task with a given description and done status.
     *
     * @param description the description of the task
     * @param done        whether the task is done or not
     */
    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Constructor for a task with a given description. Initializes the done status to false.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Toggles the done status of the task.
     *
     * @return whether the task is now considered done or not
     */
    public boolean toggleDone() {
        this.done = !this.done;
        return this.done;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    @Override
    /* Returns a string representation of the task, depending on its done status. */
    public String toString() {
        return (this.done ? "[X] " : "[ ] ") + this.description;
    }

    /**
     * Checks whether the task is considered done or not.
     *
     * @return whether the task is done
     */
    public boolean isDone() {
        return this.done;
    }
}
