package myapp.task;

/**
 * The ToDo class represents a task without any specific date or time associated with it.
 * It extends the {@link Task} class and provides implementations for methods to return string representations
 * of the task, both for display and for saving to a file.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the specified description.
     *
     * @param description the description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo object, including its type and description.
     *
     * @return a string representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string formatted for saving to a file, including the task type,
     * completion status, and description.
     *
     * @return a string representation of the ToDo object formatted for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
