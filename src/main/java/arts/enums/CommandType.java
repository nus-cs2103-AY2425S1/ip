package arts.enums;

/**
 * Represents the different types of commands that can be executed.
 * These commands correspond to various actions that can be performed
 * within the application, such as exiting the program, listing tasks,
 * marking tasks as done or not done, deleting tasks, and adding different
 * types of tasks like todo, deadline, and event.
 */
public enum CommandType {
    BYE,       // Command to exit the application
    LIST,      // Command to list all tasks
    MARK,      // Command to mark a task as done
    UNMARK,    // Command to mark a task as not done
    DELETE,    // Command to delete a task
    TODO,      // Command to add a todo task
    DEADLINE,  // Command to add a task with a deadline
    EVENT      // Command to add an event task
}
