public class Todo extends Task {

    /**
     * The constructor.
     *
     * @param description Todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this todo.
     *
     * @return A string representation of this todo.
     */
    @Override
    public String toString() {
        return "[TODO]" + super.toString();
    }
}
