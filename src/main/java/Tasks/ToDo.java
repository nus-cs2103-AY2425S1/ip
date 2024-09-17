package Tasks;

import Tasks.Task;

/**
 * Represents a ToDo task, which is a type of task that does not have any specific time or deadline associated with it.
 * A ToDo task has a description and can be marked as done or not done.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The string includes the task type "[T]" and the task's description and status.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
