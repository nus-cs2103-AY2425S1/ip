package tasks;

import exceptions.GrokInvalidUserInputException;

/**
 * The parent class that denotes a task, which is to be stored in a task list.
 * It stores 2 universal fields - the description of a task and its completion status.
 */
public abstract class Task implements Serializable {
    private String description;
    private boolean isDone;

    /**
     * Allows subclasses of Task to initialize the common description field, assuming that isDone is false:
     * @param description name, or title, of task
     * @throws GrokInvalidUserInputException for empty descriptions
     */
    public Task(String description) throws GrokInvalidUserInputException {
        if (description.isEmpty()) {
            throw new GrokInvalidUserInputException("Grok.Task description cannot be empty!");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Allows subclasses of Task to initialize the common description field.
     * @param description name, or title, of task
     * @param isDone whether the task has been done or not
     * @throws GrokInvalidUserInputException for empty descriptions
     */
    public Task(String description, Boolean isDone) throws GrokInvalidUserInputException {
        if (description.isEmpty()) {
            throw new GrokInvalidUserInputException("Grok.Task description cannot be empty!");
        }
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        assert description != null;
        return description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String serialize() {
        return String.join(" | ", getStatusIcon(), description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

