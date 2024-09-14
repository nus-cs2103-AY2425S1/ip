package enums;

/**
 * Represents the different types of tasks that can be managed in the application.
 * This enumeration defines the distinct categories of tasks available.
 */
public enum TaskType {
    /** Represents a task without any date or time constraints. */
    TODO,

    /** Represents a task that must be completed by a specific deadline. */
    DEADLINE,

    /** Represents a task associated with a specific time and possibly a location. */
    EVENT
}
