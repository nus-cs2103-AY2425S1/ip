package noisy;

/**
 * Represents a Todo task that extends the Task class.
 * A Todo task is a simple task with a description and a status indicating whether it is done.
 */
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }


    /**
     * Formats the Todo task in a specific format for saving to a file.
     * Format: T | description | status
     * Where:
     * - T represents the task type.
     * - description is the task description.
     * - status is "1" if the task is done, "0" otherwise.
     *
     * @return The formatted string representing the Todo task.
     */
    @Override
    public String formatTask() {
        String status = isDone ? "1" : "0";
        return "T | " + this.description + " | " + status;
    }

    /**
     * Returns the string representation of the Todo task.
     * The format includes the task type indicator and the status and description from the superclass.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
