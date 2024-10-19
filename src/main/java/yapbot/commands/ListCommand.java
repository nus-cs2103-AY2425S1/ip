package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Command for listing all tasks.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     * Displays the list of tasks currently in TaskList.
     *
     * @throws YapBotException If the TaskList is empty.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {
        String outputString = tasks.listTasks();
        String successMessage = "Retrieving Tasks...Success\nCurrent Tasks:\n";

        return successMessage + outputString;
    }

}
