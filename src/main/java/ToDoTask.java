public class ToDoTask extends Task {
    /**
     * Constructor that creates a ToDoTask object
     *
     * @param description that describes what the task is
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * String representation of the task
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
