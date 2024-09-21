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
        LIST,
        TASK,
        ERROR,
        INTRO,
        EXIT,
        SECRET,
        NORMAL,
        USER
    }
}
