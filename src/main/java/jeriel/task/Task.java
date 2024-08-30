package jeriel.task;
import jeriel.command.*;
import jeriel.util.*;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string indicating the status of the task.
     * 
     * The string returned is "X" if the task is done, and " " if the task is not done.
     * 
     * @return a string indicating the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
     * Returns the description of the task.
     * 
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if the task is done, and false otherwise.
     * 
     * @return true if the task is done, and false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    public abstract String toSaveFormat();

    /**
     * Returns a string representation of this task, in the format "[X] DESCRIPTION"
     * where X is "X" if the task is done, and " " otherwise.
     * 
     * @return a string representation of this task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
