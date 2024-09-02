package yoda.tasks;

/**
 * Represents a simple task with only a description.
 */
public class Todo extends Task {
    /**
     * Creates a Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representing the Todo task, to be saved to a file.
     *
     * @return A string in the format "T | isDone | description".
     */
    @Override
    public String getData() {
        String isDone = this.isDone ? "1" : "0";
        return "T | " + isDone + " | " + this.description;
    }

    /**
     * Returns a string representation of the Todo task, including its status and description.
     *
     * @return A string in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
