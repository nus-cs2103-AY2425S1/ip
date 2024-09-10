package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Displays a goodbye message to the user on the CLI, and does not save the task list to file.
     *
     * @param tasks The task list to be modified by the command.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Displays a goodbye message to the user on the GUI, and does not save the task list to file.
     *
     * @param tasks The task list to be modified by the command.
     * @param gui The GUI to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @return The goodbye message to be displayed to the user.
     */
    @Override
    public String executeGui(TaskList tasks, UiGui gui, Storage storage) {
        return gui.showGoodbye();
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