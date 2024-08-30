package Tasks;

import java.time.LocalDate;

/**
 * The Task class represents a general task with a description and a completion status.
 * This class is intended to be extended by more specific task types.
 */

public class Task {
    protected String action;
    protected boolean complete;

    /**
     * Constructs a Task with the specified action.
     * The task is initially not completed.
     *
     * @param action The description of the task.
     */

    public Task(String action) {
        this.action = action;
        this.complete = false;
    }

    /**
     * Constructs a Task with the specified action and completion status.
     *
     * @param action The description of the task.
     * @param complete The completion status of the task.
     */

    public Task(String action, boolean complete) {
        this.action = action;
        this.complete = complete;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, false otherwise.
     */

    public boolean isCompleted() {
        return complete;
    }

    /**
     * Marks the task as completed.
     */

    public void mark() {
        complete = true;
    }

    /**
     * Marks the task as not completed
     */

    public void unmark() {
        complete = false;
    }

    /**
     * Returns the due date of the task.
     *
     * @return The current date as a LocalDate (override in subclasses for specific behavior).
     */

    public LocalDate getDate() {
        return LocalDate.now();
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return (complete ? "X" : "O") + " | " + action;
    }
}