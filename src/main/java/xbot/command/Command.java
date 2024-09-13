package xbot.command;

import xbot.TaskList;
import xbot.exception.XBotException;
import xbot.storage.Storage;
import xbot.ui.Ui;

/**
 * Interface for command handlers.
 */
public interface Command {
    /**
     * Executes the command with the given parameters.
     *
     * @param list The TaskList object managing the current tasks.
     * @param ui The Ui object for interacting with the user.
     * @param storage The Storage object for saving tasks.
     * @param rest The remaining input string after the command.
     * @return The output string resulting from the command execution.
     * @throws XBotException If the command execution fails.
     */
    String execute(TaskList list, Ui ui, Storage storage, String rest) throws XBotException;
}
