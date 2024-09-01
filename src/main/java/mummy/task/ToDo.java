package mummy.task;

/**
 * Represents a task of type ToDo.
 * Inherits from the Task class.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task with the given description.
     * The task is initially set as not done.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a new ToDo task with the given description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone      The completion status of the ToDo task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileRecord() {
        return String.format("T | %d | %s", this.isDone() ? 1 : 0, this.getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
