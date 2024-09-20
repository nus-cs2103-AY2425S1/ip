package Majima.task;

public class Todo extends Task {
    /**
     * Creates a Todo task.
     *
     * @param description The description of the TODO task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "[T] | " + getStatusIcon() + " | " + getDescription();
    }
}
