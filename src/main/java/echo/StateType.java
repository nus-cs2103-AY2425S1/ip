package echo;
/**
 * Enum representing the different states that can be used to manage
 * task-related states in the application.
 */
public enum StateType {
    /**
     * State indicating that the description of a TODO task is being handled.
     */
    TODO_DESCRIPTION,

    /**
     * State indicating that the description of a DEADLINE task is being handled.
     */
    DEADLINE_DESCRIPTION,

    /**
     * State indicating that the deadline date of a DEADLINE task is being handled.
     */
    DEADLINE_DEADLINE,

    /**
     * State indicating that the description of an EVENT task is being handled.
     */
    EVENT_DESCRIPTION,

    /**
     * State indicating that the start date of an EVENT task is being handled.
     */
    EVENT_START,

    /**
     * State indicating that the end date of an EVENT task is being handled.
     */
    EVENT_END,

    /**
     * State indicating that no specific task state is currently active.
     */
    NO_STATE;
}
