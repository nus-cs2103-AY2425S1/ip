package alex.task;

/**
 * Represents the most basic Task that only has a description.
 */
public class Todo extends Task {
    public Todo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "[T]" + super.toString();
    }
}
