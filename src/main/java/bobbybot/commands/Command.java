package bobbybot.commands;

import bobbybot.BobbyBotException;
import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

/**
 * Represents a command in BobbyBot.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets the command as an exit command.
     */
    public void setExit() {
        isExit = true;
    }

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
