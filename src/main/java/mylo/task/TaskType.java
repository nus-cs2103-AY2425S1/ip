package mylo.task;

/**
 * Represents the different types of tasks in the task management system.
 * <p></p>
 * <p>The {@code TaskType} enum defines the various categories of tasks
 * that can be managed within the application. Each type corresponds to
 * a specific kind of task with distinct characteristics:</p>
 *
 * <ul>
 *     <li><strong>TODO</strong>: A simple task that needs to be done.</li>
 *     <li><strong>EVENT</strong>: A task that occurs at a specific time or within a time frame.</li>
 *     <li><strong>DEADLINE</strong>: A task that must be completed by a certain due date.</li>
 * </ul>
 *
 * @author cweijin
 */
public enum TaskType {
    TODO, EVENT, DEADLINE
}
