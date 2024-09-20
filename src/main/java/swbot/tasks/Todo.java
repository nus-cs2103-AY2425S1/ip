package swbot.tasks;

/**
 * Represents a Todo task which inherits from the Task class.
 * A Todo is a task that does not have any specific date/time associated with it.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object that represents a task without any specific date/time associated with it.
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo task to a specific file format.
     *
     * @return a formatted string representing the Todo task, including its type,
     *         completion status, and description.
     */
    public String toFileFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }

    /**
     * Returns a string representation of the Todo task.
     * The string includes the type of task ([T]) along with
     * its status and description.
     *
     * @return a string that represents the Todo task showing its type,
     *         completion status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
