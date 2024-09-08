package miku.command;

import miku.utility.Storage;
import miku.utility.TaskList;
import miku.utility.Response;

/**
 * Displays all the tasks in the list.
 */
public class ShowListCommand extends Command {

    /**
     * Initialises a ShowListCommand
     */
    public ShowListCommand() {
    };

    /**
     * Execute the command to print the task in the task list.
     *
     * @param taskList The task list that takes in the new task.
     * @param ui       The UI to perform printing.
     * @param storage  The storage function to store the data into a text file.
     */
    @Override
    public void execute(TaskList taskList, Response ui, Storage storage) {
        ui.setResponse(ui.printListResponse(taskList));
    }

}
