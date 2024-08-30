package xbot.task;

/**
 * Represents the different types of tasks in the XBot application.
 * Each task type is represented by a single character:
 * <ul>
 *     <li>{@code T}: ToDo task</li>
 *     <li>{@code D}: Deadline task</li>
 *     <li>{@code E}: Event task</li>
 * </ul>
 */
public enum TaskType {
    /**
     * Represents a ToDo Task.
     */
    T,

    /**
     * Represents a Deadline Task.
     */
    D,

    /**
     * Represents a Event Task.
     */
    E
}