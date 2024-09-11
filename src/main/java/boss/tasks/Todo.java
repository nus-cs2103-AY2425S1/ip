package boss.tasks;

import boss.tasks.Task;

/**
 * Represents the Todo class, which is a type of task,
 * users can add to their list.
 */

public class Todo extends Task {

    /**
     * Creates a Todo object
     * @param description description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a Todo object
     * @param description description of task
     * @param isDone status of task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
