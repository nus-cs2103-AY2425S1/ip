package moimoi.util.task;

import java.time.LocalDate;

/**
 * Represents a task.
 */
public class Task {

    protected TaskEnum taskEnum;
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task of the specified type and description.
     *
     * @param taskEnum Type of task.
     * @param description Task description.
     */
    public Task(TaskEnum taskEnum, String description) {
        this.taskEnum = taskEnum;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns icon of the task's completion status.
     *
     * @return Task completion status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone (i.e., unmarks the task).
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task occurs on the specified date.
     *
     * @param date Date to be checked against the task's scheduled date(s).
     * @return true if the task occurs on the specified date; false otherwise.
     */
    public boolean occursOn(LocalDate date) {
        return false;
    }

    /**
     * Checks if the task contains the specified keyword (case-insensitive) in its description.
     *
     * @param keyword Keyword to be checked within the task description.
     * @return true if the task contains the specified keyword in its description; false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.toUpperCase().contains(keyword.toUpperCase());
    }

    /**
     * Returns the String representation of the task for UI display.
     *
     * @return String representation of the task for UI display.
     */
    public String stringUI() {
        return "[" + this.taskEnum.name() + "][" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the String representation of the task for storage.
     *
     * @return String representation of the task for storage.
     */
    public String stringStorage() {
        return this.taskEnum.name() + " | " + this.getStatusIcon() + " | " + this.description;
    }

}
