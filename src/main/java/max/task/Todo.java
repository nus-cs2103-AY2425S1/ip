package max.task;

/**
 * The Todo class represents a task that needs to be done,
 * without any specific deadline or time frame.
 * It is a subclass of the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task,
     * including its status and description.
     * The format is "[T][status] description", where the status is either "X" (done) or " " (not done).
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the Todo task to a string format suitable for saving to a file.
     * The format is "T | status | description", where the status is either 1 (done) or 0 (not done).
     *
     * @return A string format of the Todo task for file saving.
     */
    @Override
    public String toFileFormat() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}
