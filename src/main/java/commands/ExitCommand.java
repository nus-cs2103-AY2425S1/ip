package commands;

import main.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Represents a command to terminate the application.
 * This command displays a farewell message to the user and exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a farewell message to the user.
     * Since this command exits the application, it does not modify the task list or storage.
     *
     * @param taskList the list of tasks (not used in this command)
     * @param ui the user interface for interacting with the user
     * @param storage the storage handler (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage("Bye bro seeya later!");
    }
}
