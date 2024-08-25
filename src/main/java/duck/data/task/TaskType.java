package duck.data.task;

/**
 * Enum representing the different types of tasks in the application.
 * The available task types are TODO, DEADLINE, and EVENT.
 */
public enum TaskType {
    /** Represents a simple to-do task with no specific deadline or event time. */
    TODO,

    /** Represents a task with a specific deadline by which it needs to be completed. */
    DEADLINE,

    /** Represents a task that occurs within a specified time period. */
    EVENT
}
