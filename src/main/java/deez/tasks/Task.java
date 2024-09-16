package deez.tasks;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Abstract class representing a task.
 */
public abstract class Task implements Serializable {
    protected static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("MMM dd yyyy " + "hh:mma");
    protected boolean isDone;
    protected String description;

    protected String[] tags;

    /**
     * Constructor for a task with a given description and done status.
     *
     * @param description the description of the task
     * @param isDone      whether the task is done or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tags = new String[0];
    }

    /**
     * Constructor for a task with a given description. Initializes the done status to false.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new String[0];
    }

    /**
     * Toggles the done status of the task.
     *
     * @return whether the task is now considered done or not
     */
    public boolean toggleIsDone() {
        this.isDone = !this.isDone;
        return this.isDone;
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
        String tagsDescription = Arrays.stream(tags).map(s -> " #" + s).collect(Collectors.joining());
        return (this.isDone ? "[X] " : "[ ] ") + this.description + tagsDescription;
    }

    /**
     * Checks whether the task is considered done or not.
     *
     * @return whether the task is done
     */
    public boolean isDone() {
        return this.isDone;
    }
    /**
     * Sets the tags of the task.
     *
     * @param tags the new tags to be set
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    /**
     * Retrieves the current tags of the task.
     *
     * @return an array containing the task's tags
     */
    public String[] getTags() {
        return this.tags;
    }
}
