package commands;

import exceptions.EchoException;
import tasks.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * This class provides functionality to remove a specific task based on its index in the task list.
 */
public class DeleteCommand {

    /**
     * Deletes a task from task list.
     * Deletes the target task from task list according to the index
     * and print the current list size.
     *
     * @param commandArray command user input.
     * @param allTasks task list.
     * @return A string confirming that the task has been deleted, along with the updated task list size.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public static String run(String[] commandArray, TaskList allTasks) throws EchoException {
        if (commandArray.length < 2) {
            throw new EchoException("Oops! Your delete command is invalid.");
        }

        try {
            int deleteIdx = Integer.parseInt(commandArray[1]) - 1;
            return allTasks.delete(deleteIdx);
        } catch (AssertionError e) {
            String message = "";
            if (allTasks.getSize() == 0) {
                message = "Oops! There is no task in the list. ";
            } else {
                message = "There is only " + allTasks.getSize() + " tasks in the list. ";
            }

            throw new EchoException(message + "Please enter a valid index!");
        }
    }

    /** Sends help information of command 'delete' to user */
    public static String getHelpMessage() {
        return "Delete a task from task list. \n\n"
                + "Format:\n"
                + "\t delete<whitespace>[index]\n"
                + "- [index] is the index of task you want to delete.\n\n"
                + "Remark: Please enter only one whitespace and correct index.";
    }
}
