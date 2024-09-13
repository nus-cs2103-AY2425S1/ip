package milo.tasks;

/**
 * Represents the different possible types of tasks in the task management system.
 */
public class TaskTypes {

    /**
     * Enumeration of task types.
     */
    public enum TaskType {
        /**
         * Represents a TO DO task.
         */
        TODO,

        /**
         * Represents an EVENT task.
         */
        EVENT,

        /**
         * Represents a DEADLINE task.
         */
        DEADLINE,

        /**
         * Represents an INVALID task type.
         */
        INVALID,

        /**
         * Represents a DATE task.
         */
        DATE
    }
}
