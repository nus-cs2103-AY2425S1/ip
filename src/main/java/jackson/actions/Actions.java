package jackson.actions;

/**
 * Enum class to hold Action types
 */
public class Actions {

    /**
     * Available action types
     */
    public enum ActionType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        SECRET,
        INVALID
    }
}
