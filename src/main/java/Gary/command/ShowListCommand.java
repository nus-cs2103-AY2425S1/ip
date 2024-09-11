package Gary.command;

import Gary.Storage;
import Gary.TaskList;
import Gary.Ui;

/**
 * Represents a command to display the current list of tasks to the user.
 */
public class ShowListCommand extends Command {

    /**
     * Executes the show list command, which displays all tasks in the {@code TaskList}.
     *
     * @param taskList The {@code TaskList} object containing tasks to be displayed.
     * @param ui The {@code Ui} object for user interaction, used to display the tasks.
     * @param storage The {@code Storage} object for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskLists(taskList);
    }

    /**
     * Indicates that the ShowListCommand does not terminate the application.
     *
     * @return false as the show list command does not terminate the application.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
