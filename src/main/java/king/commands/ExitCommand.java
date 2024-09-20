package king.commands;

import king.Storage;
import king.TaskList;
import king.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a goodbye message to the user.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface to display the goodbye message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code true} as this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
