package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Displays a goodbye message to the user.
     *
     * @param tasks The task list that stores all tasks.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that the scanner loop should end.
     *
     * @return true, indicating the scanner loop should end.
     */
    @Override
    public boolean isEndScanner() {
        return true;
    }
}