package killua.command;

import killua.storage.Storage;
import killua.ui.Ui;
import killua.util.TaskList;

/**
 * Represents a command to exit the application.
 * This command signals that the application should terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the application.
     * Updates the user interface to show a goodbye message.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface to interact with.
     * @param storage The storage to save the task list (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExitMessage();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true, as this command signifies that the application should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
