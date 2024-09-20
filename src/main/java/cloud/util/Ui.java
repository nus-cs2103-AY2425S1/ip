package cloud.util;

import cloud.command.CommandType;

/**
 * Handles the response formatting for Cloud replies
 */
public class Ui {

    public String showMarked(String taskStatus) {
        return String.format("Task marked as done!\n" + taskStatus);
    }

    public String showUnmarked(String taskStatus) {
        return String.format("Task marked as not done\n" + taskStatus);
    }

    public String showList(String list) {
        return String.format("Here is a list of all your tasks:\n" + list);
    }

    public String showMatching(String listString) {
        if (listString.isEmpty()) {
            return "There are no matching tasks found";
        }
        return String.format("Here are the matching tasks in your list:\n" + listString);
    }

    public String showAddedTask(TaskList taskList) {
        return String.format(
                "Added the following task:\n\t%s\nNow you have %d task%s in the list\n",
                taskList.getLatestTask(),
                taskList.getTaskCount(),
                taskList.getTaskCount() != 1 ? "s" : ""
        );
    }

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

    public String showHelp(CommandType commandType) {
        if (commandType == null) {
            return showHelpAll();
        }
        return showHelpFor(commandType);
    }
}
