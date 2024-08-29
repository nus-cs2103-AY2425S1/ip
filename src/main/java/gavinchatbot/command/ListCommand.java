package gavinchatbot.command;

import gavinchatbot.task.TaskList;
import gavinchatbot.util.Ui;
import gavinchatbot.util.Storage;
import gavinchatbot.util.GavinException;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command, displaying all tasks to the user.
     *
     * @param tasks The task list containing tasks to be displayed.
     * @param ui The UI that will display the list of tasks.
     * @param storage The storage (not used in this command).
     * @throws GavinException If there is an error displaying the list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException {
        try {
            ui.showList(tasks);
        } catch (GavinException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns whether the command causes the application to exit.
     *
     * @return false as the list command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
