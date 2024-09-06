package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Task;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Command for marking a task as completed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates a MarkCommand instance.
     *
     * @param commandDetails Details specifying the task.
     * @throws YapBotException If commandDetails is empty.
     */
    public MarkCommand(String commandDetails) throws YapBotException {
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
     * Marks the task as completed.
     *
     * @throws YapBotException if the task cannot be found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {

        Task task = tasks.markTask(index);
        String successMessage = "Finding Task...Success\nTask Completed:\n  " + task;

        return successMessage;
    }


}
