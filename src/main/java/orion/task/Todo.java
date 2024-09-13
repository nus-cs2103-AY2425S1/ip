package orion.task;

/**
 * Represents a Todo task with a unique ID and description.
 *
 * <p>
 * This class extends the {@link Task} class and provides the specific
 * type icon for Todo tasks.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified ID and description.
     *
     * @param taskID      the unique identifier for the Todo task.
     * @param description the description of the Todo task.
     */
    public Todo(int taskID, String description) {
        super(taskID, description);
    }

    /**
     * Returns the type icon for a Todo task.
     *
     * @return the type icon "[T]" for Todo tasks.
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}
