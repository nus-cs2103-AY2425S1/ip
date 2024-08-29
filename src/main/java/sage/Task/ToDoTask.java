package sage.Task;

public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask with a description.
     *
     * @param description The description of the todo task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return String.format("T | %s", super.toSave());
    }
}
