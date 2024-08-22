public class Todo extends Task {
    /**
     * Constructor for Todo class.
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task.
     * @return task description with status
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
