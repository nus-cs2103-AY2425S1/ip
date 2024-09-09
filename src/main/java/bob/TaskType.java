package bob;

/**
 * Represents the different types of tasks that can be managed in the task list.
 */
public enum TaskType {
    /**
     * Represents a task of type ToDo, which is a basic task without any deadlines or time constraints.
     */
    TODO,

    /**
     * Represents a task with a specific deadline by which it needs to be completed.
     */
    DEADLINE,

    /**
     * Represents an event that has a start and end time.
     */
    EVENT,

    /**
     * Represents a task that takes a fixed amount of time to complete but does not have a fixed start/end time.
     */
    FIXED;
}
