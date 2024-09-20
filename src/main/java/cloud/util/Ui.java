package cloud.util;

import cloud.command.CommandType;

/**
 * Handles the response formatting for Cloud replies.
 * <p>
 * The <code>Ui</code> class provides methods to format and display various types of
 * messages to the user, including task updates, listing of tasks, and command help information.
 * </p>
 */
public class Ui {

    /**
     * Formats a message indicating a task has been marked as done.
     *
     * @param taskStatus the status of the marked task
     * @return a formatted string indicating the task has been marked as done
     */
    public String showMarked(String taskStatus) {
        return String.format("Task marked as done!\n" + taskStatus);
    }

    /**
     * Formats a message indicating a task has been marked as not done.
     *
     * @param taskStatus the status of the unmarked task
     * @return a formatted string indicating the task has been marked as not done
     */
    public String showUnmarked(String taskStatus) {
        return String.format("Task marked as not done\n" + taskStatus);
    }

    /**
     * Formats a message displaying a list of all tasks.
     *
     * @param list the list of tasks
     * @return a formatted string showing the list of all tasks
     */
    public String showList(String list) {
        return String.format("Here is a list of all your tasks:\n" + list);
    }

    /**
     * Formats a message showing tasks that match a find query.
     *
     * @param listString the list of matching tasks
     * @return a formatted string showing the matching tasks or a message if no tasks match
     */
    public String showMatching(String listString) {
        if (listString.isEmpty()) {
            return "There are no matching tasks found";
        }
        return String.format("Here are the matching tasks in your list:\n" + listString);
    }

    /**
     * Formats a message indicating a task has been added.
     *
     * @param taskList the TaskList with the added task
     * @return a formatted string indicating the added task and the total number of tasks
     */
    public String showAddedTask(TaskList taskList) {
        return String.format(
                "Added the following task:\n\t%s\nNow you have %d task%s in the list\n",
                taskList.getLatestTask(),
                taskList.getTaskCount(),
                taskList.getTaskCount() != 1 ? "s" : ""
        );
    }

    /**
     * Formats a message indicating a task has been deleted.
     *
     * @param taskList the TaskList with the remaining tasks
     * @param deletedTaskStatus the status of the deleted task
     * @return a formatted string indicating the deleted task and the remaining number of tasks
     */
    public String showDeletedTask(TaskList taskList, String deletedTaskStatus) {
        return String.format(
                "Removed the following task:\n\t%s\n%d task%s remaining\n",
                deletedTaskStatus,
                taskList.getTaskCount(),
                taskList.getTaskCount() != 1 ? "s" : ""
        );
    }

    private String showHelpAll() {
        return "Here are the available commands:\n"
                + CommandType.LIST + "\n"
                + CommandType.TODO + "\n"
                + CommandType.DEADLINE + "\n"
                + CommandType.EVENT + "\n"
                + CommandType.DELETE + "\n"
                + CommandType.MARK + "\n"
                + CommandType.UNMARK + "\n"
                + CommandType.FIND + "\n"
                + CommandType.HELP + "\n";
    }

    private String showHelpFor(CommandType commandType) {
        return commandType.toString();
    }

    /**
     * Formats a help message based on the command type.
     * If no command type is provided, returns a list of all available commands.
     *
     * @param commandType the command type for specific help information
     * @return a formatted help message
     */
    public String showHelp(CommandType commandType) {
        if (commandType == null) {
            return showHelpAll();
        }
        return showHelpFor(commandType);
    }
}
