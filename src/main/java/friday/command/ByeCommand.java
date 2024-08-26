package friday.command;

import friday.task.TaskList;
import friday.util.Storage;
import friday.util.Ui;

/**
 * Represents a command to exit the application.
 * This command will print a goodbye message and signal that the application should exit.
 */
public class ByeCommand extends Command {

    /**
     * Executes the goodbye command, displaying a farewell message to the user.
     *
     * @param tasks   The task list to be used by the command. Not used in this command.
     * @param ui      The user interface for interacting with the user.
     * @param storage The storage for saving the task list. Not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return true, as this command indicates that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
