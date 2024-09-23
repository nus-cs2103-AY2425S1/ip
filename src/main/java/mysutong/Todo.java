package mysutong;

/**
 * Represents a todo task in the MySutong application.
 * A todo task is a simple task with a description and a completion status.
 * Extends {@link Task}.
 */
class Todo extends Task {

    /**
     * Constructs a new Todo instance with the specified description.
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description); // Call to the superclass (Task) constructor.
    }

    /**
     * Returns a string representation of the todo task, which includes the task type
     * prefixed by "[T]" and the status and description from the superclass.
     *
     * @return a string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString(); // Include the task type in the toString output.
    }
}
