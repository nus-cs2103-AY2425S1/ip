package asta.command;

/**
 * The Command enum represents the different types of commands that the Asta application can recognize and execute. Each
 * enum constant corresponds to a specific user command.
 */
public enum Command {

    /**
     * Command to mark a task as done.
     */
    MARK,

    /**
     * Command to unmark a task, marking it as not done.
     */
    UNMARK,

    /**
     * Command to add a new ToDo task.
     */
    TODO,

    /**
     * Command to add a new Deadline task.
     */
    DEADLINE,

    /**
     * Command to add a new Event task.
     */
    EVENT,

    /**
     * Command to delete an existing task.
     */
    DELETE,

    /**
     * Command to list all current tasks.
     */
    LIST,
    /**
     * Command to find task by keyword.
     */
    FIND,

    /**
     * Command to exit the application.
     */
    BYE,
    UNKNOWN
}
