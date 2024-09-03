package bob.tasks;

import java.time.LocalDate;

/**
 * Represents a todo task in the task list.
 */
public class TodoTask extends Task {
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
