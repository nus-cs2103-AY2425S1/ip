package tick.commands;

import tick.storage.Storage;
import tick.storage.TaskList;
import tick.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     *
     * @param tasks TaskList where tasks are stored.
     * @param ui The user interface to display information to the user.
     * @param storage The storage file to be updated.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true as the command is an exit command.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
