package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Ui;

public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     * Displays the list of tasks currently in TaskList.
     *
     * @throws YapBotException If the TaskList is empty.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws YapBotException {
        String outputString = tasks.listTasks();
        String successMessage = "Retrieving Tasks...Success\nCurrent Tasks:\n";
        ui.printOutput(successMessage + outputString);

        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
