package papadom.commands;

import papadom.storage.Storage;
import papadom.storage.TaskList;
import papadom.utils.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface for outputting messages.
     * @param storage The storage system for saving the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.output(taskList.outputList());
    }
}
