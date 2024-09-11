package hana.task;

import hana.HanaException;

/**
 * Represents a task with a description and completion status.
 * This is a base class for different types of tasks.
 */
public abstract class Task {
    public static final int DEFAULT_PRIORITY = 3;
    public static final int MIN_PRIORITY = 1;
    public static final int MAX_PRIORITY = 3;

    protected String description;
    protected boolean isDone;
    protected int priority;

    /**
     * Constructs a new Task with description.
     *
     * @param description The description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = DEFAULT_PRIORITY;
    }

    /**
     * Sets the priority level of the task.
     *
     * @param priority The priority level to set (1 is highest, 3 is lowest).
     */
    public void setPriority(int priority) throws HanaException {
        if (priority < MIN_PRIORITY || priority > MAX_PRIORITY) {
            throw new HanaException("Priority must be between " + MIN_PRIORITY + " and " + MAX_PRIORITY);
        }
        this.priority = priority;
    }

    /**
     * Returns the priority level of the task.
     *
     * @return The priority level of the task.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Change task completion status.
     *
     * @param isDone Completion status to change to.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return The completion status of the task.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the details of the task.
     *
     * @return The details of the task.
     */
    @Override
    public String toString() {
        return "[" + priority + "]" + (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns the details of the task to save as.
     *
     * @return The details of the task to save as.
     */
    public abstract String fileString();

    public String getDescription() {
        return this.description;
    }
}
