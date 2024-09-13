package bocchi.task;

import bocchi.exception.BocchiException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a base task.
 */
public abstract class Task implements Serializable {
    /**
     * The task description.
     */
    protected String description;

    /**
     * Whether this task is done.
     */
    protected boolean isDone;

    /**
     * The tags of this task.
     */
    protected List<String> tags;

    /**
     * The constructor.
     *
     * @param description Task description.
     * @throws BocchiException If the description is empty.
     */
    public Task(String description) throws BocchiException {
        if (description == null) {
            throw new BocchiException("So..sorry, but the task description can't be empty.");
        }
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * The constructor with tags.
     *
     * @param description Task description.
     * @param tags        .Task tags.
     * @throws BocchiException If the description is empty.
     */
    public Task(String description, List<String> tags) throws BocchiException {
        if (description == null) {
            throw new BocchiException("So..sorry, but the task description can't be empty.");
        }
        this.description = description;
        this.isDone = false;
        this.tags = tags;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUnDone() {
        isDone = false;
    }


    public boolean getDone() {
        return isDone;
    }


    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Returns a string representation of this task.
     *
     * @return A string representation of this task.
     */
    @Override
    public String toString() {
        String tagsString = tags.isEmpty() ? "{}" : "{#" + String.join(" #", tags) + "}";
        String doneString = isDone ? "[X]" : "[ ]";

        return doneString + " " + tagsString + " " + description;
    }
}
