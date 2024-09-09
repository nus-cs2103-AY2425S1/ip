package yapper.command;
/**
 *  Represents the type of command that the user has entered.  
 *  The command type is used to determine the action that Yapper should take.
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
    UNKNOWN,
    FIND
}
