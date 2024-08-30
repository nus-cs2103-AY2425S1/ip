package puke.tasks;

import java.util.Objects;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task's completion status as a tick box.
     *
     * @return "[X]" if the task is done, "[ ]" if it is not done
     */
    public String tickBox() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return tickBox() + " " + description;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
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
     * Converts the task to its file format representation.
     *
     * @return the string representation of the task in file format
     */
    public abstract String toFileFormat();

    /**
     * Compares this task to another object for equality.
     *
     * @param obj the object to compare this task to
     * @return true if the object is equal to this task, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return isDone() == task.isDone() &&
                Objects.equals(getDescription(), task.getDescription());
    }

    /**
     * Returns the hash code of the task.
     *
     * @return the hash code of the task
     */
    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), isDone());
    }
}
