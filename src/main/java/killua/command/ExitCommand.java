package killua.command;

import killua.util.Storage;
import killua.util.TaskList;
import killua.util.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
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