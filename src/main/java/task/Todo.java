package task;

/**
 * The Todo class represents a simple task with a name.
 * It inherits from the Task class and does not have a specific deadline or event period.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified name.
     * The task is initially marked as not done.
     *
     * @param name The name or description of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a Todo task with the specified name and completion status.
     *
     * @param name   The name or description of the task.
     * @param isDone Whether the task is marked as completed.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Converts the Todo task to a string format suitable for saving to a file.
     *
     * @return The string representation of the task for saving to a file.
     */
    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        return "T," + done + "," + this.getName();
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
