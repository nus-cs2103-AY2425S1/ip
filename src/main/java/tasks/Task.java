package tasks;

import exceptions.InvalidPriorityException;

/**
 * Represents a task.
 * Encapsulates the description of a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Initialises a Task object with a default priority of low.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /**
     * Initialises a Task object with the priority set by the user.
     *
     * @param description description of the task.
     * @param priority priority of the task.
     */
    public Task(String description, String priority) {
        this.description = description;
        this.isDone = false;
        setPriority(priority);
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task in a string.
     *
     * @return status icon.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the string of the task to be saved to data file.
     *
     * @return string representing the task information.
     */
    public String writeTask() {
        int priorityNumber = this.priority == Priority.HIGH
                ? 1
                : this.priority == Priority.MEDIUM
                ? 2
                : 3;
        return (this.isDone ? 1 : 0) + "," + priorityNumber + "," + this.description;
    }

    /**
     * Returns description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets priority of the task to the given priority.
     *
     * @param priority the priority to be set to.
     */
    public void setPriority(String priority) {
        try {
            this.priority = Priority.stringToPriority(priority);
        } catch (InvalidPriorityException e) {
            System.out.println(e);
        }
    }

    /**
     * Returns priority of task.
     *
     * @return the priority of the task.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return string of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
