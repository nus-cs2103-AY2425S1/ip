package chatgpt.command;

import chatgpt.storage.Storage;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;

/**
 *  The ListCommand class represents a command to display the task within the TaskList.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     *
     * In ListCommand, it returns a string representing the task within the local TaskList.
     *
     * @param tasks that tracks the application's tasks
     * @param ui that handles the printing and reading on inputs and outputs
     * @param storage that handles saving and reading text file with saved data
     * @return String that represents the task within the local TaskList
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
