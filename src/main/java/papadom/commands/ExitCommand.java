package papadom.commands;

import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.Ui;
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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exitMessage();
    }
}
