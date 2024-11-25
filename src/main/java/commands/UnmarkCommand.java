package commands;

import exceptions.EchoException;
import tasks.TaskList;

/**
 * Represents a command to unmark a task as incomplete in the task list.
 * This class provides functionality to update the status of a specific task to indicate
 * it is no longer completed, based on its index in the task list.
 */
public class UnmarkCommand {

    /**
     * Unmarks the complete status of a target task from task list.
     *
     * @param commandArray command user input.
     * @param allTasks task list.
     * @return A string confirming that the task has been marked as not done.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public static String run(String[] commandArray, TaskList allTasks) throws EchoException {
        if (commandArray.length < 2) {
            throw new EchoException("Oops! Your unmark command is invalid.");
        }

        try {
            int unmarkIdx = Integer.parseInt(commandArray[1]) - 1;
            return allTasks.unmarkTask(unmarkIdx);
        } catch (AssertionError e) {
            throw new EchoException("There is " + allTasks.getSize()
                    + " tasks in the list. Please enter a valid index.");
        }
    }

    /** Sends help information of command 'unmark' to user */
    public static String getHelpMessage() {
        return "Unmark a task in the task list as incomplete.\n\n"
                + "Format:\n"
                + "\t unmark<whitespace>[index] \n"
                + "- [index] is the index of task you want to unmark. \n\n"
                + "Remark: Please enter only one whitespace and correct index.";
    }
}
