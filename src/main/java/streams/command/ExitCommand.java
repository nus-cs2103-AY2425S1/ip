package streams.command;

import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying a goodbye message.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface to display the goodbye message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates that this is an exit command.
     *
     * @return true, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
