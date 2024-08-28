package count.task;

/**
 * The ToDo class extends the Task class
 * changing the String representation to include the [T] symbol
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean completion) {
        super(description, completion);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
