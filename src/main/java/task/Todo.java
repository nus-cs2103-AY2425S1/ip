package task;

public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} with the specified description.
     * This constructor initializes the {@code Todo} object with the given description
     * and sets its status to not done by default.
     *
     * @param description the description of the to-do item
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do item.
     * The string includes the to-do type indicator "[T]" followed by the string representation
     * of the task obtained from the superclass.
     *
     * @return a formatted string representing the to-do item in the format:
     *         "[T] description"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a raw string representation of the to-do item.
     * This method returns the same string as {@code toString}, which includes the "[T]"
     * type indicator followed by the task's description.
     *
     * @return a string representing the to-do item in the format:
     *         "[T] description"
     */
    @Override
    public String toRawString() {
        return this.toString();
    }
}