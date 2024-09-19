package Alex.Task;

/**
 * Represents a default task with a description.
 * A default task is a basic task without any additional properties like deadlines or events.
 */
public class DefaultTask extends Task {

    /**
     * Constructs a DefaultTask with the specified description.
     * @param description The description of the task.
     */
    public DefaultTask(String description) {
        super(description);
    }

    /**
     * Gets the type of the task.
     * @return The type of the task (DEFAULT).
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEFAULT;
    }

    /**
     * Gets the string representation of the DefaultTask.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "added: " + super.getDescription();
    }
}
