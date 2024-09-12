package sammy.command;

import sammy.Storage;
import sammy.task.TaskList;
import sammy.Ui;

public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all the tasks in the TaskList.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}

