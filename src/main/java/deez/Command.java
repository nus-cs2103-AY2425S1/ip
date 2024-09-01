/**
 * Enums for commands.
 */
package deez;

/**
 * Enum representing various commands that can be executed by this program.
 */

public enum Command {

    /**
     * Exit the program.
     */
    EXIT,

    /**
     * List all tasks.
     */
    LIST,

    /**
     * Mark a task as completed.
     */
    MARK,

    /**
     * Unmark a task.
     */
    UNMARK,

    /**
     * Create a new todo item.
     */
    TODO,

    /**
     * Set a deadline for a task.
     */
    DEADLINE,

    /**
     * Schedule an event.
     */
    EVENT,

    /**
     * Delete a task.
     */
    DELETE,

    /**
     * Save the current state of tasks.
     */
    SAVE,

    /**
     * Find a specific task based on its name or description.
     */
    FIND,

    /**
     * Unknown command.
     */
    UNKNOWN;
}
