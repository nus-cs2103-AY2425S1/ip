package lolo.command;

import lolo.Ui;
import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.TaskList;

/**
 * Represents a command to exit the application.
 * This command displays a farewell message to the user and
 * signals that the application should terminate.
 */
class ExitCommand extends Command {

    /**
     * Executes the command by displaying a goodbye message to the user.
     * This command does not modify the task list or storage.
     *
     * @param tasks The list of tasks, which is not modified by this command.
     * @param ui The user interface to display the goodbye message.
     * @param storage The storage, which is not used by this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command is an exit command.
     * Subclasses can override this method to return true if the command should terminate the application.
     *
     * @return true, as this command is meant to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
