package vinegar.command;

import vinegar.task.TaskList;
import vinegar.storage.Storage;
import vinegar.ui.Ui;

/**
 * Exits the application.
 * <p>
 * The ExitCommand class is responsible for terminating the application
 * and displaying a goodbye message to the user.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by showing a goodbye message to the user.
     *
     * @param tasks   The TaskList containing all tasks (not used in this command).
     * @param ui      The Ui for displaying messages to the user.
     * @param storage The Storage for saving the updated task list (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Indicates that the exit command has been called.
     *
     * @return True since this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
