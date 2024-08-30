package alex.task;

/**
 * Represents the most basic Task that only has a description.
 */
public class Todo extends Task {
    public Todo(String taskName, boolean taskCompletionStatus) {
        super(taskName, taskCompletionStatus);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String storageString() {
        return "[T]" + super.toString();
    }
}
