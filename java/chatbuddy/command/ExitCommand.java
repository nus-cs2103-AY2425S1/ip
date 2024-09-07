package chatbuddy.command;

import chatbuddy.storage.Storage;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying the farewell message.
     *
     * @param tasks   The task list (unused in this command).
     * @param ui      The user interface to display the farewell message.
     * @param storage The storage (unused in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getOutput();
    }

    /**
     * Returns true, indicating that this command will terminate the application.
     *
     * @return true as the command will exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
