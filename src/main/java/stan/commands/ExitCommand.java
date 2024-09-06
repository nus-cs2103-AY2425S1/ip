package stan.commands;

import stan.Storage;
import stan.TaskList;
import stan.ui.Ui;


/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param tasks The task list (unused in this command).
     * @param ui The UI object to display the goodbye message.
     * @param storage The storage object (unused in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }

    /**
     * Indicates that this command will exit the application.
     *
     * @return true, since this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
