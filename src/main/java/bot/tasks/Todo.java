package bot.tasks;

import bot.enums.TaskSymbol;

/**
 * Represents a todo task.
 *
 * @author mongj
 */
public class Todo extends Task {
    /**
     * Creates a new <code>Todo</code> object.
     *
     * @param description of the event.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new <code>Deadline</code> object.
     *
     * @param description of the event.
     * @param isDone indicating if the task has been marked completed.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return TaskSymbol.TODO.name + " | " + super.toData();
    }
}
