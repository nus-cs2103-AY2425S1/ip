package stan.commands;

import stan.Storage;
import stan.TaskList;
import stan.ui.Ui;


/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param tasks The task list to be displayed.
     * @param ui The UI object to display the task list.
     * @param storage The storage object (unused in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
