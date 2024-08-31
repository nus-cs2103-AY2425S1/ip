package Task.TaskType;

import Task.Task;

/**
 * Represents a to-do task.
 * This class extends the {@link Task} class and represents a task that needs to be done without any specific time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the specified description.
     *
     * @param description A description of the to-do task.
     */
    public Todo(String description) {
        super(TaskType.TODO, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
