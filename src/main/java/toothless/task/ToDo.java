package toothless.task;

/**
 * Represents a ToDos task.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDos.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for ToDos.
     *
     * @param description the description of the task
     * @param isDone      the status of the task
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
