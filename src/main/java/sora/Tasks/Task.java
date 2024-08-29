package sora.Tasks;

import java.util.List;

/**
 * Task is a generic task.
 */
public abstract class Task {
    /** Status of the task. Either Done or Not Done. */
    protected boolean isDone;
    /** Description of the task */
    protected String description;

    /**
     * Constructs a new Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Returns status of task
     * If task is done, "X" is returned.
     * If task is not done, " " is returned.
     *
     * @return Status of task.
     */
    public String getStatus() {
        return isDone ? "X" : " ";
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

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }

    /**
     * Returns a list containing task details.
     *
     * @return List of String.
     */
    public abstract List<String> getTaskDetails();
}
