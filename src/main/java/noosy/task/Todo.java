package noosy.task;

/**
 * Represents a todo, which is a specific type of task with no deadline or duration.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given name.
     * The status is set to false (not completed) by default.
     *
     * @param name The name or description of the todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a new Todo with the given name and status.
     *
     * @param name   The name or description of the todo.
     * @param status The completion status of the todo.
     */
    public Todo(String name, boolean status) {
        super(name, status);
    }

    /**
     * Returns a string representation of the todo for file storage.
     * The format is "T | status | name", where status is inherited from the Task class.
     *
     * @return A formatted string containing the todo's type, status, and name.
     */
    @Override
    public String storeInFileAsString() {
        return String.format("T | %s | %s", super.storeInFileAsString(), this.name);
    }

    /**
     * Returns a string representation of the todo, including its type and the base Task representation.
     *
     * @return A string representation of the todo, prefixed with "[T]".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
