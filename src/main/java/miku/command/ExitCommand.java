package miku.command;

import miku.utility.UI;
import miku.utility.TaskList;
import miku.utility.Storage;

/**
 * Prints the farewell message and serves as an exit indicator.
 */
public class ExitCommand extends Command {

    /**
     * Initialise an exit command.
     */
    public ExitCommand(){}

    /**
     * Execute the command to print the farewell message in an UI.
     *
     * @param taskList The task list that takes in the new task.
     * @param ui The UI to perform printing.
     * @param storage The storage function to store the data into a text file.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.farewell();
    }
}
