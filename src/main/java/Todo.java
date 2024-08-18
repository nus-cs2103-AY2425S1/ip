// A class that represents a Todo task, which is a subclass of Task.

public class Todo extends Task {
    /**
     * Constructor creates a new Todo task with the specified description.
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
