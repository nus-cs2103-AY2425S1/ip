package king.commands;

import king.Storage;
import king.TaskList;
import king.ui.Ui;

/**
 * Represents a command that displays the list of tasks to the user.
 * This command is used to show all the tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying the task list to the user.
     *
     * @param tasks The task list containing the tasks to be displayed.
     * @param ui The user interface used to show the task list.
     * @param storage The storage (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code false} as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
