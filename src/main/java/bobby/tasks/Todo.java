package bobby.tasks;

/**
 * Represents a Todo task.
 * A Todo is a type of task with a description but without any specific time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * The format includes the type of task and the output of the Task's toString method.
     *
     * @return A string in the format "[T][statusIcon] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task formatted for saving to a file.
     * The format is "T | isDone | description".
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "T" + " " + super.toFileString();
    }
}
