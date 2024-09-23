package mysutong;

/**
 * Represents a todo task in the MySutong application.
 * A todo task is a simple task with a description, completion status, and priority.
 * Extends {@link Task}.
 */
class Todo extends Task {

    /**
     * Constructs a new Todo instance with the specified description.
     * The task is initialized with a default priority of "low" (priority 3).
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description); // Call to the superclass (Task) constructor.
    }

    /**
     * Constructs a new Todo instance with the specified description and priority.
     *
     * @param description the description of the todo task.
     * @param priority the priority level (1 for high, 2 for medium, 3 for low).
     */
    public Todo(String description, int priority) {
        super(description); // Call to the superclass (Task) constructor.
        setPriority(priority); // Set the priority using the method from the superclass.
    }

    /**
     * Returns a string representation of the todo task, which includes the task type
     * prefixed by "[T]" and the status, description, and priority from the superclass.
     *
     * @return a string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString(); // Include the task type in the toString output.
    }

    /**
     * Returns a string representation suitable for file storage.
     * Includes the task type, status, description, and priority for this todo task.
     *
     * @return a formatted string representing the todo task for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description + " | " + getPriority();
    }
}
