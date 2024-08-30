package talkie.task;

/**
 * Represents a task with no specific deadline or time, commonly referred to as a "ToDo".
 * <p>
 * The {@code ToDo} class extends the {@code Task} class and provides a specific implementation for
 * tasks that do not have a date or time associated with them.
 * </p>
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task with the specified description.
     *
     * @param desc The description of the ToDo task.
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Serializes the ToDo task to a string format for storage or retrieval.
     * <p>
     * The string format is as follows: "T | status | description", where "status" is 1 if the task is done and 0 otherwise.
     * </p>
     *
     * @return A string representation of the todo task for storage.
     */
    public String stringifyTask() {
        return String.format("T | %d | %s", super.getStatus() ? 1 : 0, super.getDesc());
    }

    /**
     * Returns a string representation of the ToDo task for display purposes.
     * <p>
     * The format is: "[T] description", where "[T]" is a label indicating the task type.
     * </p>
     *
     * @return A string representing the ToDo task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
