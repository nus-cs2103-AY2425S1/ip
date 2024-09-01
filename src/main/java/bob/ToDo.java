package bob;

/**
 * Represents an todo task in the Bob chatbot.
 * The todo task includes a description, a completion status, and a task type.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description and type.
     *
     * @param description The description of the ToDo task.
     * @param type The type of the task, which should be {@link TaskType#TODO}.
     */
    public ToDo(String description, TaskType type) {
        super(description, type);
    }

    /**
     * Constructs a new ToDo task with the specified description, completion status, and type.
     *
     * @param description The description of the ToDo task.
     * @param isDone The completion status of the task.
     *               {@code true} if the task is marked as done, {@code false} otherwise.
     * @param type The type of the task, which should be {@link TaskType#TODO}.
     */
    public ToDo(String description, boolean isDone, TaskType type) {
        super(description, isDone, type);
    }

    /**
     * Returns a string representation of the ToDo task.
     * This method overrides the {@link Task#toString()} method to provide a specific format for ToDo tasks.
     *
     * @return The string representation of the ToDo task.
     * @inheritDoc
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Formats the todo task for saving to a file.
     * The format includes the task's description and status in the input format.
     * This method overrides the {@link Task#formatToSave()} method to provide a specific format for ToDo tasks.
     *
     * @return The string representation of the ToDo task in a format suitable for saving.
     * @inheritDoc
     */
    @Override
    public String formatToSave() {
        return super.formatToSave();
    }
}
