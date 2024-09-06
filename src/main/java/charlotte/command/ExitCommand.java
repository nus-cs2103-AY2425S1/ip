package charlotte.command;

import charlotte.storage.Storage;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

/**
 * Represents a command to terminate the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to terminate the application.
     * @param tasks The TaskList object, which is not used in this command.
     * @param ui The Ui object used to display messages to the user.
     * @param storage The Storage object, which is not used in this command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        return ui.printExit();
    }
}
