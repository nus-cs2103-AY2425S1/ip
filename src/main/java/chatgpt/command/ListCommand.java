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
     * In ListCommand, it prints the task within the given TaskList.
     *
     * @param tasks that tracks the application's tasks
     * @param ui that handles the printing and reading on inputs and outputs
     * @param storage that handles saving and reading text file with saved data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
