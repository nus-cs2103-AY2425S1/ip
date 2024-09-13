package chatgpt.command;

import chatgpt.storage.Storage;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;


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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
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
