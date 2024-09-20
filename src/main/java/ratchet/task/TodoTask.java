package ratchet.task;

/**
 * Todo class.
 */
public class TodoTask extends Task {
    /**
     * Constructor for new Todo task.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Constructor for loading Todo task.
     *
     * @param description The description of the task.
     * @param isDone      The status of the task.
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T|" + super.toSave();
    }
}
