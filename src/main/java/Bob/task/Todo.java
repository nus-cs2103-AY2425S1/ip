package bob.task;

public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
