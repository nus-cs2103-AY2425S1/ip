package tecna.command;

/**
 * Represents different types of command including valid and invalid ones.
 *
 * @author DennieDan.
 */
public enum CommandType {
    BYE,
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    DELETE,
    FIND,
    MARK,
    UNMARK,
    INVALID,
    TODO_WRONG_FORMAT,
    DEADLINE_WRONG_FORMAT,
    EVENT_WRONG_FORMAT,
    INDEX_WRONG_FORMAT
}
