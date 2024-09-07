package bobbybot.commands;

import bobbybot.BobbyBotException;
import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

/**
 * Represents a command in BobbyBot.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage.
     * @throws BobbyBotException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyBotException;
}
