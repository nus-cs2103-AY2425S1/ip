package sammy.command;

import sammy.Storage;
import sammy.task.TaskList;
import sammy.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a goodbye message to the user.
     *
     * @param tasks The list of tasks (not used in this command).
     * @param ui The UI object to interact with the user.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbyeMessage();
    }

    /**
     * Indicates that this command will exit the application.
     *
     * @return true, indicating the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

