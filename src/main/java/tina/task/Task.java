package tina.task;

import tina.TinaException;

/**
 * Represents a task with a description and completion status.
 * A <code>Task</code> object contains a description and methods to mark or unmark the task as completed.
 */
public abstract class Task {
    protected final String des;
    protected boolean isMark = false;

    /**
     * Constructs a new <code>Task</code> object with the specified description.
     *
     * @param des The description of the task.
     * @throws TinaException if the description is empty.
     */
    protected Task(String des) throws TinaException {
        if (des.isEmpty()) {
            throw new TinaException("Enter your description after the space");
        }
        this.des = des;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isMark = true;
    }

    /**
     * Unmarks the task, indicating it is not completed.
     */
    public void unmark() {
        isMark = false;
    }

    /**
     * Returns the description of the task, including its completion status.
     *
     * @return A string of the task description, with "[X]" if marked, or "[ ]" if not.
     */
    public String getDes() {
        if(isMark) {
            return "[X] " + des;
        } else {
            return "[ ] " + des;
        }
    }

    public String description() {
        return des;
    }
}
