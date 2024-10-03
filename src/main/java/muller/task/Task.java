package muller.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the task list. A task can be a Todo, Deadline, or Event.
 */
public abstract class Task {
    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected String type; // [T], [D], [E]
    private String name;
    private boolean isDone;

    /**
     * Constructs a Task object with the specified name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        assert name != null : "Task name should not be null";
        assert !name.trim().isEmpty() : "Task name should not be empty";
        this.name = name;
        this.isDone = false;
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
     * Returns a string representing whether the task is done.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String getDoneIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the name (description) of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task details.
     */
    @Override
    public String toString() {
        return this.type + getDoneIcon() + " " + name;
    }

    /**
     * Checks if the task occurs on a specified date.
     *
     * @param date The date to check.
     * @return True if the task occurs on the specified date, false otherwise.
     */
    public abstract boolean isOnDate(LocalDate date);

    /**
     * Checks if the task is due soon (e.g., within the next few days).
     *
     * @return True if the task is due soon, false otherwise.
     */
    public abstract boolean isDueSoon();

    /**
     * Converts the task to a string format suitable for saving to a file.
     *
     * @return The string representation of the task for saving.
     */
    public abstract String convertToFileString();
}


