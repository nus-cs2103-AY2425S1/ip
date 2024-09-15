package megamind.task;

import java.io.Serial;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * The `Task` class represents a generic task.
 * It implements the `Serializable` interface to allow tasks to be serialized.
 * The class provides methods to mark tasks as done or not done, and to get the task description.
 * It also includes methods to return the string representation of the task.
 */
public class Task implements Serializable {
    protected static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter
            .ofLocalizedDateTime(java.time.format.FormatStyle.MEDIUM);
    @Serial
    private static final long serialVersionUID = 1L;
    private final String description;
    private boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
}
