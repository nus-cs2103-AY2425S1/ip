package gavinchatbot.command;

import gavinchatbot.task.TaskList;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command, displaying a goodbye message to the user.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The UI that will display the goodbye message.
     * @param storage The storage (not used in this command).
     * @return A goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Returns whether the command causes the application to exit.
     *
     * @return true as the exit command causes the application to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
