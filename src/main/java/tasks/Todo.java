package tasks;

/**
 * Represents a class for todo-type task.
 */
public class Todo extends Task {

    /**
     * Represents a constructor for a Todo task.
     *
     * @param name Name for the todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Represents a constructor for a Todo task, marking it as done or undone.
     *
     * @param name Name for the todo.
     * @param done True if the task is marked as done.
     */
    public Todo(String name, boolean done) {
        super(name, done);
    }

    /**
     * Returns string format of the Task.
     *
     * @return String format of the Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns data format of the Task.
     *
     * @return Data format of the Task.
     */
    @Override
    public String toData() {
        return "T" + super.toData();
    }
}
