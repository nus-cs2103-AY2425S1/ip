package colress.task;

import java.time.LocalDate;

/**
 * Represents the ToDo task, which contains a String field representing the description
 * and a boolean field reflecting whether the task is marked as done or not.
 */
public class ToDo extends Task {
    /**
     * Constructor for the ToDo class, initialising the description field with the provided parameter.
     * When a new task is created using this constructor, the task is not done by default.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for the ToDo class, initialising both description and isDone fields with the provided parameters.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean fallsOnDate(LocalDate date) {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        if (getIsDone()) {
            return String.format("[X][T] %s", getDescription());
        } else {
            return String.format("[ ][T] %s", getDescription());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toTextFile() {
        if (getIsDone()) {
            return String.format("[X] | To-Do | %s", getDescription());
        } else {
            return String.format("[ ] | To-Do | %s", getDescription());
        }
    }
}
