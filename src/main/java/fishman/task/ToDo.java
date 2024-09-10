package fishman.task;

/**
 * Represents the to-do task.
 * This class extends the Task class, without adding any additional attributes.
 */
public class ToDo extends Task {
    /**
     * Constructs a new to-do task.
     *
     * @param description The description of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        assert description != null : "Description should not be null";
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
