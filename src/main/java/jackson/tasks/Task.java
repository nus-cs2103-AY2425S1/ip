package jackson.tasks;

import jackson.utils.CustomDateTime;

/**
 * Task class for task representation.
 */
public abstract class Task {
    // variables to store task information
    private String taskName;
    private Boolean isCompleted;

    /**
     * Constructs Task abstract class.
     * Used for subclasses to call only.
     * @param name String name of the task.
     */
    public Task(String name) {
        this.taskName = name;
        this.isCompleted = false;
    }

    // Temporal methods to extract dates and times from task
    public abstract CustomDateTime getStartDateTime();
    public abstract CustomDateTime getEndDateTime();
    public abstract String getTaskType();

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Reverts task to incomplete.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Returns boolean of whether task is completed.
     * @return true if completed, false if not.
     */
    public boolean getStatus() {
        return this.isCompleted;
    }

    /**
     * Returns representation of status of task; true if complete, false otherwise.
     * @return String representation of complete or incomplete.
     */
    public String getStatusRepresentation() {
        return this.isCompleted ? "X" : " ";
    }

    /**
     * Returns name of task.
     * @return String representation of task name.
     */
    public String getName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusRepresentation(), this.taskName);
    }
}
