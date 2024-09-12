package momo.command;

/**
 * Each constant in the enum refers to a command the Momo chatbot offers.
 * Used to identify which ui/command methods to run in the checkValidInput
 * method inside the Parser class
 */
public enum CommandType {
    BYE,
    LIST,
    FIND,
    DELETE,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    ARCHIVE
}
