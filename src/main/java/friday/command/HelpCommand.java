package friday.command;

import friday.task.TaskList;
import friday.util.Storage;
import friday.util.Ui;

/**
 * Represents a command to display help information to the user.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command, showing the available commands to the user.
     *
     * @param tasks   The task list. Not used in this command.
     * @param ui      The user interface for interacting with the user.
     * @param storage The storage for saving the task list. Not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
