package Task;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    /**
     * Returns a string representation of the Todo task
     *
     * @return a string representation of the Todo task
     */
    @Override
    public String toString() {
        assert description != null:"empty description";
        return "[T]" + super.toString();
    }
    /**
     * Returns a string representation of the Todo task to save in a file.
     *
     * @return a string representation of the Todo task for saving.
     */
    @Override
    public String save() {
        assert description != null:"empty description";
        return "T | " + super.save();
    }
}
