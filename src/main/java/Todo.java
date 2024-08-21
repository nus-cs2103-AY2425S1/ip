public class Todo extends Task{
    /**
     * Constructor for the to do class.
     *
     * @param description Description of the task.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
