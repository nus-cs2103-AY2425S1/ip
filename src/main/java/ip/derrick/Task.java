package ip.derrick;

import java.time.LocalDate;

/**
 * Task class representing a task. It contains three subclasses, Todo, Event and Deadline.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes the task with a description.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Changes the task status to DONE.
     */
    public void setMark() {
        this.isDone = true;
    }

    /**
     * Changes the task status to UNDONE.
     */
    public void setUnmark() {
        this.isDone = false;
    }

    /**
     * Returns an 'X' if the task is marked as DONE, otherwise returns " ".
     *
     * @return A string indicating the task's status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String changeFormat() {
        return this.description;
    }

    public LocalDate convertDate(String date) {
        return null;
    }
}
