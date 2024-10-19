package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Task;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Command for marking a task as incomplete.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates an UnmarkCommand instance.
     *
     * @param commandDetails Details specifying the task..
     * @throws YapBotException If commandDetails is empty.
     */
    public UnmarkCommand(String commandDetails) throws YapBotException {
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
     * Marks the task as incomplete.
     *
     * @throws YapBotException if the task cannot be found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {

        Task task = tasks.unmarkTask(index);
        String successMessage = "Finding Task...Success\nTask Incomplete:\n  " + task;

        return successMessage;
    }


}
