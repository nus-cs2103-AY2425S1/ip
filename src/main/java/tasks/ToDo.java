package tasks;

import java.io.Serializable;

/**
 * Represents a task to be completed.
 * This class extends the Task class and implements Serializable for object serialization.
 */
public class ToDo extends Task implements Serializable {

    /**
     * Creates a task to be completed.
     *
     * @param task without any date/time attached to it.
     */
    public ToDo(String task) {
        this.task = task;
    }

    /**
     * Returns the information of the task.
     *
     * @return information the task in "[T][-] Task" format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
