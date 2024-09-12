package task.tasktype;

import task.Task;

/**
 * Represents a to-do task.
 * This class extends the {@link Task} class and represents a task that needs to be done without
 * any specific time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the specified description.
     *
     * @param description A description of the to-do task.
     */
    public Todo(String description, String... tags) {
        super(TaskType.TODO, description, tags);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "tags: " + this.getTagsAsString();
    }
}
