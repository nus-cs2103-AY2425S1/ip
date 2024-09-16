package com.appleaster.task;

/**
 * Represents an abstract task in the Appleaster application.
 * This class serves as a base for specific task types like Todo, Deadline, and Event.
 */
public abstract class Task {
    /** The description of the task */
    protected String description;

    /** The status of the task (done or not done) */
    protected TaskStatus status;

    /** The type of the task */
    protected TaskType type;

    /**
     * Constructs a Task with the given description and type.
     *
     * @param description the description of the task
     * @param type the type of the task
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
        this.type = type;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param completed true if the task is completed, false otherwise
     */
    public void setCompleted(boolean completed) {
        this.status = completed ? TaskStatus.DONE : TaskStatus.NOT_DONE;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isCompleted() {
        return this.status == TaskStatus.DONE;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task.
     * This method is abstract and should be implemented by subclasses.
     *
     * @return a string representation of the task
     */
    @Override
    public abstract String toString();
}