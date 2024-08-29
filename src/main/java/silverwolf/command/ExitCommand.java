package silverwolf.command;

import silverwolf.storage.Storage;
import silverwolf.task.TaskList;
import silverwolf.ui.Ui;

/**
 * The ExitCommand class represents a command to terminate the application.
 * It extends the abstract Command class and provides the logic to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a goodbye message to the user
     * and terminating the application.
     *
     * @param tasks The TaskList object representing the list of tasks.
     * @param ui The Ui object used for interacting with the user.
     * @param storage The Storage object used for saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye(); // Display a goodbye message to the user.
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return true, since this command terminates the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
