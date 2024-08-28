package commands;

import main.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Represents a command to list all tasks in the task list.
 * This command displays all tasks currently stored in the application.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     * This method uses the user interface to show each task in the list.
     *
     * @param taskList the list of tasks to be displayed
     * @param ui the user interface for interacting with the user
     * @param storage the storage handler (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTasks(taskList);  // Display all tasks
    }
}
