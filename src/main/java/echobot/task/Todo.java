package echobot.task;

/**
 * Represents a todo task.
 * A todo task is a type of task that does not have any specific deadline or event date.
 */
public class Todo extends Task {

    /**
     * Creates a todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * The format is "[T] description", where [T] denotes the todo task type.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    /**
     * Returns a string representation of the todo task in a format suitable for saving to a file.
     * The format is "T | isDone | description", where T denotes the todo task type.
     *
     * @return A string representation of the todo task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
}
