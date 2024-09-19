package Naega.Task;

public class Todo extends Task {

    /**
     * Creates a new Todo task with the specified description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task in a user-friendly format.
     * The format includes the task type and description.
     *
     * @return a string representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task in a format suitable for saving.
     * The format includes the task type, completion status, and description.
     *
     * @return a string representation of the Todo task in save format
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}