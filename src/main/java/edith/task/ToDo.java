package edith.task;

/**
 * Represents a ToDo task, which is a simple task without a specific date or time.
 * This class extends the abstract Task class.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task with the specified description.
     *
     * @param task The description of the ToDo task.
     */
    public ToDo(String task) {
        super("[T] ", task);
    }
}
