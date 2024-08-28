package command;

import exception.DudeException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

public class InvalidCommand extends Command {
    /**
     * Throws a DukeException to indicate that the command is invalid.
     *
     * @param tasks The task list to be listed.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DudeException To indicate that the command is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException {
        throw new DudeException("I'm sorry, but I don't know what that means :(");
    }

    /**
     * Returns false because this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
