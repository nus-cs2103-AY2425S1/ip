package commands;

import tasks.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * This class provides functionality to retrieve and display all tasks, including their details,
 * from the task list in a specified order.
 */
public class ListCommand {

    /** Lists all tasks in the task list */
    public static String run(TaskList allTasks) {
        return allTasks.listAllTasks();
    }

    /** Sends help information of command 'list' to user */
    public static String getHelpMessage() {
        return "Lists all tasks with their details in the task list in order.\n\n"
                + "Format:\n"
                + "\t list\n"
                + "with no whitespace.";
    }
}
