package jackson.enums;

/**
 * Enum class to hold Action types.
 */
public class Commands {

    /**
     * Available Command Types.
     * Only for GUI style logic.
     */
    public enum CommandType {
        TASKS,
        MODIFY,
        ERROR,
        INTRO,
        EXIT,
        SECRET,
        NORMAL,
        USER
    }
}
