package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Task;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Command for deleting a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand instance.
     *
     * @param commandDetails Details containing the task to be deleted.
     * @throws YapBotException If commandDetails is empty.
     */
    public DeleteCommand(String commandDetails) throws YapBotException {
        if (commandDetails.isEmpty()) {
            throw new YapBotException("Error, User Input Prediction module offline."
                    + "\nTask number must be manually entered (eg. \"1\", \"2\").");
        }

        if (commandDetails.contains(" ")) {
            index = Integer.parseInt(commandDetails.substring(0, commandDetails.indexOf(" "))) - 1;
        } else {
            index = Integer.parseInt(commandDetails) - 1;
        }
    }

    /**
     * {@inheritDoc}
     * Removes the task from the TaskList.
     *
     * @throws YapBotException if the task cannot be found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {

        Task task = tasks.deleteTask(index);
        String successMessage = "Finding Task...Success\nTask deleted from database:\n  " + task;

        return successMessage;
    }

}
