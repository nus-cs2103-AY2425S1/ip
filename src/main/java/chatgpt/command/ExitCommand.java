package chatgpt.command;

import chatgpt.exception.ChatBotException;
import chatgpt.task.TaskList;

import chatgpt.ui.Ui;

import chatgpt.storage.Storage;

/**
 *  The DeleteCommand class represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * In ExitCommand, display exit message and exits the program.
     *
     * @param tasks that tracks the application's tasks
     * @param ui that handles the printing and reading on inputs and outputs
     * @param storage that handles saving and reading text file with saved data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * {@inheritDoc}
     *
     * In ExitCommand, it will always return true.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
