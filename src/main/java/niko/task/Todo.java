package niko.task;

/**
 * Represents a task of type TODO.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
