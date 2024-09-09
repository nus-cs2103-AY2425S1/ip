package bobby.ui;

import java.time.LocalDateTime;

/**
 * Represents a ToDo task, which is a type of task with only a description.
 * This class extends the {@link Task} class and provides specific formatting
 * for storing and displaying ToDo tasks.
 */
public class ToDos extends Task {
    /**
     * Constructs a new bobby.ui.ToDos task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toStore() {
        return "T/" + super.getStatus() + "/" + description;
    }
    @Override
    public LocalDateTime getBy() {
        return null;
    }
    @Override
    public LocalDateTime getFrom() {
        return null;
    }
}
