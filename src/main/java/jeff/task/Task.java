package jeff.task;

import java.time.LocalDate;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for the Task Class.
     * Marked as not done.
     *
     * @param description Description of the task. Marked as not done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for the Task Class.
     * Could be marked as done or not done.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is completed or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of whether the task is done or not.
     *
     * @return The string representation of the task status.
     */
    private String getStatusIcon() {
        return this.isDone ? "[X]" : "[  ]";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns the file string representation of the task to be stored in the task list text file.
     *
     * @return the file string representation of the task.
     */
    public String toFileString() {
        if (this.isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the task status.
     *
     * @return true if the task is done and false if the task is not done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns false.
     *
     * @param date Given date.
     * @return false.
     */
    public boolean isOnThisDate(LocalDate date) {
        return false;
    }

    /**
     * Checks if the task description contains the given name.
     *
     * @param name Given name to check against.
     * @return true if the task description contains the given name and false otherwise.
     */
    public boolean doesDescriptionContain(String name) {
        return this.description.toLowerCase().contains(name.toLowerCase().trim());
    }
}
