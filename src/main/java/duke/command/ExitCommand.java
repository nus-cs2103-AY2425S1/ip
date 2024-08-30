package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit the Duke application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command, displaying a goodbye message to the user.
     *
     * @param tasks The task list of the application (not used in this command).
     * @param ui The user interface to interact with the user.
     * @param storage The storage system of the application (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns whether this command will terminate the application.
     *
     * @return true, as the exit command is meant to terminate the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}