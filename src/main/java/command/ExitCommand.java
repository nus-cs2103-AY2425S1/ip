package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * Displays a goodbye message to the user.
     *
     * @param tasks   The TaskList object containing the current list of tasks (not used in this command).
     * @param ui      The Ui object for interacting with the user.
     * @param storage The Storage object for saving tasks to the storage file (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI object cannot be null";
        assert storage != null : "Storage object cannot be null";

        return ui.displayGoodbye();
    }

    /**
     * Indicates whether this command should cause the program to exit.
     *
     * @return true, as this command causes the program to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}