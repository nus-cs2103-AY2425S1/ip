package command;

/**
 * The CommandType enum defines the different types of commands that can be recognized and
 * executed in the Schedulo application.
 * Each constant represents a specific command type that the user can input.
 */
public enum CommandType {
    /**
     * Represents the command to list all tasks.
     */
    LIST,

    /**
     * Represents the command to mark a task as done.
     */
    MARK,

    /**
     * Represents the command to unmark a task as not done.
     */
    UNMARK,

    /**
     * Represents the command to delete a task from the list.
     */
    DELETE,

    /**
     * Represents the command to add a new Todo task.
     */
    TODO,

    /**
     * Represents the command to add a new Deadline task.
     */
    DEADLINE,

    /**
     * Represents the command to add a new Event task.
     */
    EVENT,

    /**
     * Command to find tasks containing a specific word.
     */
    FIND,

    /**
     * Represents the command to add multiple todo tasks at once.
     */
    ADDMULTIPLETODOS,


    /**
     * Represents the command to exit the application.
     */
    BYE,

    /**
     * Represents an invalid command input by the user.
     */
    INVALID;
}
