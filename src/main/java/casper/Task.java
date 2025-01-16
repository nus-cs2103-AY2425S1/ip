package casper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents the abstract class Task
 */
public abstract class Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
    private final String description;
    private final String taskType;
    private boolean isDone;

    /**
     * Constructor for the class Task
     * To be used in subclasses only
     * @param description Description of the task
     * @param taskType Represents the specific task type
     * @param isDone Represents the completion status of the task
     */
    public Task(String description, String taskType, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.taskType, this.getStatusIcon(), this.description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Formats the display of date according to the class variable FORMATTER
     * @param date The date to be formatted
     * @return The formatted date as a string
     */
    protected String formatDate(LocalDate date) {
        return date.format(FORMATTER);
    }
}
