package elysia.tasks;

/**
 * Represents a simple task that needs to be done, without any specific date or time.
 * Extends the Task class and provides a unique identifier for ToDo tasks.
 */
public class Todo extends Task{

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo task to a string format suitable for saving to a file.
     * The format is "T" followed by the format from Task.
     *
     * @return A string representation of the ToDo task in file format.
     */
    @Override
    public String toFile() {
        return "T" + super.toFile();
    }

    /**
     * Returns a string representation of the ToDo task, including its description.
     * The format is "[T]" followed by the format from Task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
