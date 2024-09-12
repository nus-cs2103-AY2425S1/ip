package duck.commands;

/**
 * Enum representing the different command words that the Duck application can process.
 * Each value corresponds to a specific command type that the application recognizes
 * and handles accordingly.
 */
public enum CommandWord {
    /**
     * Command to display help information.
     */
    HELP,

    /**
     * Command to list all tasks.
     */
    LIST,

    /**
     * Command to find tasks by a keyword.
     */
    FIND,

    /**
     * Command to sort tasks based on the arguments.
     */
    SORT,

    /**
     * Command to mark a task as done.
     */

    MARK,

    /**
     * Command to unmark a task as incomplete.
     */
    UNMARK,

    /**
     * Command to delete a task.
     */
    DELETE,

    /**
     * Command to add a ToDo task.
     */
    TODO,

    /**
     * Command to add a Deadline task.
     */
    DEADLINE,

    /**
     * Command to add an Event task.
     */
    EVENT,

    /**
     * Command to list tasks due on a specific date.
     */
    ON,

    /**
     * Command to exit the application.
     */
    BYE
}
