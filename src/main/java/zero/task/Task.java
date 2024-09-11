package zero.task;

/**
 * The {@code Task} class represents a task with a description and a completion status.
 * It provides methods to mark the task as done or not done, retrieve the task's status,
 * and format the task's details for display or storage.
 * Mainly used as a super class for other tasks with more information.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done tasks with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String isDone() {
        return this.isDone ? "1" : "0";
    }

    public String toFormatted() {
        return this.description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
