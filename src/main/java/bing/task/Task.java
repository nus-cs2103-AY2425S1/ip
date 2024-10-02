package bing.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and status (done or not done).
 */
public abstract class Task {
    private String info;
    private TaskStatus isDone;


    /**
     * Constructs a Task with the given description.
     *
     * @param info the description of the task
     */
    public Task(String info) {
        this.info = info;
        this.isDone = TaskStatus.UNDONE;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getInfo() {
        return info;
    }

    /**
     * Returns the status of the task (done or not done).
     *
     * @return the task status
     */
    public TaskStatus getStatus() {
        return isDone;
    }

    /**
     * Sets the status of the task to done or not done.
     *
     * @param status the new task status
     */
    public void setStatus(TaskStatus status) {
        this.isDone = status;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the task as a string
     */
    public abstract String toString();

    /**
     * Returns the task in a format suitable for saving to a file.
     *
     * @param formatter the DateTimeFormatter for formatting date and time
     * @return the task in file format
     */
    public abstract String toFileFormat(DateTimeFormatter formatter);
}
