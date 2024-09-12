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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Task list cannot be null";
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";

        ui.showGoodbye();
        return ui.showGoodbyeAsString();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
