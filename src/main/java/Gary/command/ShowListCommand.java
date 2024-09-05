package Gary.command;

import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;

/**
 * Represents a command to display the current list of tasks to the user.
 */
public class ShowListCommand extends Command {

    /**
     * Executes the ShowListCommand. Displays the current list of tasks to the user
     * through the UI.
     *
     * @param taskLists The task list that contains all the tasks.
     * @param ui        The UI object to interact with the user and display the task list.
     * @param storage   The storage object (unused in this command).
     */
    @Override
    public void execute(TaskList taskLists, Ui ui, Storage storage) {
        ui.showTaskLists(taskLists);
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


