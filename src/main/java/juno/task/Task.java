package juno.task;

import java.time.LocalDateTime;

import com.google.gson.annotations.Expose;

/**
 * Represents a task with a description, whether it is done, and type of task.
 * Provides relevant task related methods.
 * Superclass for other task types such as Event, Deadline and Todo.
 */
public class Task {
    @Expose
    protected String description;
    @Expose
    protected boolean isDone;
    @Expose
    protected String taskType;

    /**
     * Constructs a Task instance that takes in a specified description and task type.
     *
     * @param description The description of the task.
     * @param taskType The type of the task (e.g. "todo", "deadline", "event").
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns whether task is done with an icon, for the task.
     * "X" if the task is marked as done, else, a space.
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done (true).
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done (false).
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return True if the task is done, otherwise false.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task type.
     *
     * @return The task type.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public LocalDateTime getEndTime() {
        return null;
    }

}
