package talkie.command;

/**
 * Represents the different types of commands that can be issued in the Talkie application.
 * Each command type corresponds to a specific user action or task manipulation within the application.
 */
public enum CommandType {
    BYE, LIST, DELETE,
    MARK, UNMARK, TODO,
    EVENT, DEADLINE,
}
