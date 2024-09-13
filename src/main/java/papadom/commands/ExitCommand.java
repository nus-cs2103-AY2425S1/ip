package papadom.commands;

import papadom.storage.Storage;
import papadom.storage.TaskList;
import papadom.utils.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the application.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface for outputting messages.
     * @param storage The storage system for saving the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.exitMessage();
    }
}
