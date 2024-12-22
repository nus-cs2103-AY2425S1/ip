package colress.task;

import java.time.LocalDate;

/**
 * Encapsulates behaviour of a task. Every task minimally has a String field that represents
 * its description, and a boolean field that reflects whether the task is marked as done or not.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task, initialising the description field with the provided parameter.
     * When a new task is created using this constructor, the task is not done by default.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task, initialising both description and isDone fields with the provided parameters.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done by assignment isDone to be true.
     */
    public void check() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by assignment isDone to be false.
     */
    public void uncheck() {
        this.isDone = false;
    }

    /**
     * Checks if the task falls on the provided date and returns the result.
     */
    public abstract boolean fallsOnDate(LocalDate date);

    /**
     * Checks if task contains a specified keyword in its description and returns the result.
     */
    public boolean containsInDescription(String keyword) {
        return this.description.toLowerCase().contains(keyword);
    }

    /**
     * Returns a string representation of the task with delimiters to facilitate file reading.
     */
    public abstract String toTextFile();
}
