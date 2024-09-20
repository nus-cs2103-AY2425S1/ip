package gojou.task;

/**
 * Represents the most basic Task that only has a description.
 */
public class Todo extends Task {

    public Todo(String taskName, boolean isCompleted, Priority priority) {
        super(taskName, isCompleted, priority);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "[T]" + super.toStorageString();
    }
}
