package task;

import exceptions.DelphiException;
import parser.Parser;

/**
 * Abstract class representing a generic task. Specific tasks like
 * task.Todo, task.Deadline, and task.Event inherit from this class.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a task.Task with a given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.name = description;
        this.isDone = false;
    }

    /**
     * this function is used to update tasks in the tasklist with a different deadline or time frame
     *
     * @param newInfo the new time frame to update tasks with
     * @param p       parser object to help parse the new info
     * @throws DelphiException if the input is incorrect
     */
    public abstract void editTask(String newInfo, Parser p) throws DelphiException;

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void uncomplete() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and name.
     *
     * @return The string representation of the task.
     */

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
