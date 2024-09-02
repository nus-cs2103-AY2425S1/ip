package rose.task;

/**
 * Represents the different types of tasks available in the application.
 *
 * <p>The {@code TaskType} enum defines three types of tasks that can be created:
 * <ul>
 *   <li>{@link #TODO} - A simple task that needs to be done.</li>
 *   <li>{@link #EVENT} - A task that starts at a specific date/time and ends at a specific date/time.</li>
 *   <li>{@link #DEADLINE} - A task that needs to be completed by a specific date or time.</li>
 * </ul>
 * </p>
 *
 * @see rose.task.Task
 */
public enum TaskType {
    TODO, EVENT, DEADLINE
}
