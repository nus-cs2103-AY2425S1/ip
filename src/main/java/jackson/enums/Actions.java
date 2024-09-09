package jackson.enums;

/**
 * Enum class to hold Action types.
 */
public class Actions {

    /**
     * Available action types.
     */
    public enum ActionType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        SORT,
        BYE,
        SECRET,
        INVALID
    }
}
