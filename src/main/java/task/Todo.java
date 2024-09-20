package task;

/**
 * The Todo class extends Task and represents a task with a description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string format of todo
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
