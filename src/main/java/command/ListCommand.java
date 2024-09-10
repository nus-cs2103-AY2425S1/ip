package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Displays the task list to the user on the CLI,
     * and does not save the task list to file.
     *
     * @param tasks The task list to be displayed by the command.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }

    /**
     * Displays the task list to the user on the GUI,
     * and does not save the task list to file.
     *
     * @param tasks The task list to be displayed by the command.
     * @param gui The GUI to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @return The task list to be displayed to the user.
     */
    @Override
    public String executeGui(TaskList tasks, UiGui gui, Storage storage) {
        return gui.showTaskList(tasks.getTasks());
    }
}