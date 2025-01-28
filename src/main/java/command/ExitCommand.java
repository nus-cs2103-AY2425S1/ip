package command;

import exception.DynamikeException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     *
     * @param tasks The task list to be listed.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DynamikeException If there is an error listing the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DynamikeException {
        ui.showGoodbye();
    }

    /**
     * Returns true because this is an exit command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
