/**
 * Represents a task for the fishman bot.
 * This class contains the details of a task.
 */
abstract class Task {
    /** The details of the task. */
    protected String description;
    /** The indicator for whether the task is completed. */
    protected boolean isDone;

    /**
     * Constructs a new Task object with the given detail and completion indicator.
     *
     * @param detail The details of the task.
     */
    public Task(String detail) {
        this.description = detail;
        this.isDone = false;
    }

    /**
     * Display and mark the task done.
     *
     * @return "X" if the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     * This method sets the 'isDone' status of the task to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     * This method sets the 'isDone' status of the task to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String getTaskType();

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + (isDone ? "X" : " ") + "] " + description;
    }
}
