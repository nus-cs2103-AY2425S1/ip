package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;


/**
 * Command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param tasks   TaskList to be displayed.
     * @param ui      UI to handle user interaction.
     * @param storage Storage (not used in this command).
     * @return Result message showing the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
