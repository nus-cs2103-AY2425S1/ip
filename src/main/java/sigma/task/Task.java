package sigma.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 * A task has a description and a status.
 * The status of a task can be either completed or not completed.
 */
public abstract class Task {
    private boolean isCompleted;
    private final String description;

    /**
     * Creates a task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
    }

    /**
     * Setter for completed boolean
     *
     * @param completed
     */
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    /**
     * Getter for the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task.
     *
     * @return String representation of the status of the task.
     */
    public String getStatusString() {
        return isCompleted ? "X" : " ";
    }

    public abstract String getTaskType();

    public abstract LocalDateTime getStartDate();

    public abstract String getDateString();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusString(), getDescription());
    }


}
