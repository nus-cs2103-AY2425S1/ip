/**
 * The {@code ToDo} class represents a task that needs to be done,
 * without any specific deadline or time frame. It is a subclass of {@link Task}.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the specified description.
     * The task type is set to {@link TaskType#TODO}.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a string representation of the ToDo task,
     * including its type indicator and description.
     *
     * @return A string in the format: "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
