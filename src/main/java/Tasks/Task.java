package Tasks;

import Exceptions.DelphiException;
import Exceptions.EmptyInputException;

/**
 * Abstract class representing a generic task. Specific tasks like
 * Tasks.Todo, Tasks.Deadline, and Tasks.Event inherit from this class.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a Tasks.Task with a given description.
     *
     * @param description The description of the task.
     * @throws EmptyInputException if the description is empty.
     */
    public Task(String description) throws DelphiException {
        if (!description.isEmpty() && description.charAt(0) != '/') {
            this.name = description;
            this.isDone = false;
        } else {
            throw new EmptyInputException();
        }
    }

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
