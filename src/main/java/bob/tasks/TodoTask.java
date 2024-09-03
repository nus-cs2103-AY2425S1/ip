package bob.tasks;

import java.time.LocalDate;

/**
 * Represents a todo task in the task list.
 */
public class TodoTask extends Task {

    /**
     * Creates a new TodoTask object.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public LocalDate getDate() {
        return null;
    }
    @Override
    public String getType() {
        return "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
