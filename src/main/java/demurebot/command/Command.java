package demurebot.command;

import demurebot.DemureBotException;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command that can be executed by the bot.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param list TaskList object that contains the list of tasks.
     * @param ui Ui object that handles the user interface.
     * @throws DemureBotException If an error occurs during the execution of the command.
     */
    public abstract String execute(TaskList list, Ui ui) throws DemureBotException;
}
