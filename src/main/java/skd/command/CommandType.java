package skd.command;

/**
 * Represents the different types of commands that the parser can interpret from user input.
 */
public enum CommandType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    ETC,
    FIND,
    SNOOZE;
}
