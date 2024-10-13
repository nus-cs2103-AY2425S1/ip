package tira.task;


/**
 * Represents a task of type ToDo, which is a basic task without any specific
 * deadline or event period.
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
     * Returns a string representation of the ToDo task, which includes
     * the type identifier "[T]" and the task's status and description.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
