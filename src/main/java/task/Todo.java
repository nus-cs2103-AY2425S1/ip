package task;

/**
 * The Todo task represents a Task to be done
 */
public class Todo extends Task {
    public static final String TASK_TYPE = "T";
    public Todo(String description) {
        super(description, Todo.TASK_TYPE);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
