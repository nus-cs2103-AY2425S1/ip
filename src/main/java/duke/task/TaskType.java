package duke.task;

/**
 * Represents the different types of tasks available in the Duke application.
 */
public enum TaskType {
    /** Represents a task that is a to-do item. */
    TODO,
    
    /** Represents a task that has a deadline with a specific due date. */
    DEADLINE,
    
    /** Represents an event task with a specific start and end time. */
    EVENT;
}