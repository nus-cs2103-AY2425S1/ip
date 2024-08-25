package megamind.task;

import java.time.format.DateTimeFormatter;
import java.io.Serial;
import java.io.Serializable;

public class Task implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    protected static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter
            .ofLocalizedDateTime(java.time.format.FormatStyle.MEDIUM);
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

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "✔️" : "❌") + "] " + description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return description;
    }
}
