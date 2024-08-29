package command;
public enum CommandType {
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,

    /**
     * Command to find tasks containing a specific word.
     */
    FIND,

    BYE,
    INVALID;
}
