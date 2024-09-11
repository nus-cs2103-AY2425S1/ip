package Gary.command;

import Gary.Storage;
import Gary.TaskList;
import Gary.Ui;


/**
 * Represents a ByeCommand which terminates the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand by displaying a goodbye message to the user.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The UI object to display the goodbye message.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
    }

    /**
     * Indicates whether this command causes the application to terminate.
     *
     * @return true because ByeCommand terminates the application.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
