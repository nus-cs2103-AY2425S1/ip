/**
 * Represents the ToDo task.
 * This class extends the Task class, without adding any additional attributes.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task.
     *
     * @param description The description of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
