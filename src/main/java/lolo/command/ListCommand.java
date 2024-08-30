package lolo.command;

import lolo.Ui;
import lolo.storage.Storage;
import lolo.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * This command interacts with the user interface to display
 * the current list of tasks to the user.
 */
class ListCommand extends Command {

    /**
     * Executes the command by displaying the current list of tasks to the user.
     * This command does not modify the task list or storage.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui The user interface to show the task list.
     * @param storage The storage, which is not used by this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
