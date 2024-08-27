package GPT;
/**
 * The TaskType enum represents the types of tasks that can exist in the GPT application.
 * It defines three types of tasks: TODO, DEADLINE, and EVENT.
 */
public enum TaskType {
    /**
     * Represents a task that needs to be done but does not have a specific deadline or time frame.
     */
    TODO,
    /**
     * Represents a task that has a specific deadline by which it must be completed.
     */
    DEADLINE,
    /**
     * Represents a task that occurs during a specific time period, with a start and end time.
     */
    EVENT
}
