package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;

/**
 * Represents a command that displays help information to the user.
 */

public class HelpCommand extends Command {
    /**
     * Executes the help command by requesting the UI to display help information.
     *
     * @param tasks The list of tasks (not used in this command).
     * @param ui The UI instance for displaying help messages.
     * @param storage The storage instance (not used in this command).
     * @return The help information to be displayed.
     * @throws InvalidCommandException If any error occurs while processing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        return ui.showHelp();
    }

    /**
     * Returns false as this command does not terminate the application.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
