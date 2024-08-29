package moody.commands;

import moody.storage.Storage;
import moody.tasks.TaskList;
import moody.ui.Ui;

/**
 * Represents a command to exit the application.
 * This command displays a goodbye message to the user and signals the application to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by showing a goodbye message to the user.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface for showing messages.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
