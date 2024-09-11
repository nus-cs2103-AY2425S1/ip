package lexi.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a date and time associated with it.
 * This is an abstract class that extends the {@link Task} class.
 * Subclasses of {@code DatedTask} should provide specific implementations for tasks
 * that have associated dates and times, such as events or deadlines.
 */
public abstract class DatedTask extends Task {

    /**
     * The formatter used for outputting date and time in the format "d MMM yyyy, HH:mm".
     * This static formatter can be used by subclasses to ensure consistent formatting
     * across all date-related tasks.
     */
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");

    /**
     * Constructs a new {@code DatedTask} with the specified task name.
     * This constructor initializes the task with a name, and the task is initially not done.
     *
     * @param taskName The name of the task.
     */
    public DatedTask(String taskName) {
        super(taskName);

        // Precondition: Ensure task name is not null or empty
        assert taskName != null && !taskName.isEmpty() : "Task name cannot be null or empty.";
    }

    /**
     * Returns the date and time formatter used by the task.
     *
     * @return The DateTimeFormatter for outputting the date and time.
     */
    public DateTimeFormatter getOutputFormatter() {
        // Ensure that the formatter is initialized
        assert outputFormatter != null : "OutputFormatter should be initialized.";
        return DatedTask.outputFormatter;
    }
}
