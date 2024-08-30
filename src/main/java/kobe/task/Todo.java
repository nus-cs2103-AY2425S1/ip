package kobe.task;

/**
 * Represents a todo task in the Duke chatbot application.
 * A todo task is a task that needs to be done but does not have a specific deadline or event timing.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified name.
     *
     * @param name The name or description of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the string representation of the todo task for saving to a file.
     *
     * @return A formatted string representing the todo task in a file-friendly format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

    /**
     * Returns the string representation of the todo task, including its type indicator.
     *
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
