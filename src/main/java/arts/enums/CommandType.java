package arts.enums;

/**
 * Represents the different types of commands that can be executed.
 * These commands correspond to various actions that can be performed
 * within the application, such as exiting the program, listing tasks,
 * marking tasks as done or not done, deleting tasks, and adding different
 * types of tasks like todo, deadline, and event.
 */
public enum CommandType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    FIND,
    SORT_DEADLINES,
    SORT_EVENTS
}
