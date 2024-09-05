package slave;

/**
 * Todo is a child class of Task that contains a description of the task only
 */
public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    protected Todo(boolean isCompleted, String task) {
        super(isCompleted, task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        // no dates involved, returns the same thing as toString()
        return toString();
    }
}
