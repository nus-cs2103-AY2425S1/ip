package skywalker.command;

import skywalker.storage.Storage;
import skywalker.task.TaskList;
import skywalker.ui.Ui;

public class ListCommand extends Command {

    /**
     * shows all the tasks in the task list.
     *
     * @param tasks   The task list on which the command will operate.
     * @param ui      The UI object used to interact with the user.
     * @param storage The storage object used to save or load the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
