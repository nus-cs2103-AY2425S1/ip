package Dook.Commands;

import Dook.Tasks.TaskList;
import Dook.Storage.Storage;
import Dook.Ui.Ui;
import Dook.DookException;

/**
 * The command that lists all the tasks in the TaskList.
 */
public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DookException {
        ui.separate();
        if (taskList.numOfTasks() == 0) {
            throw new DookException("No tasks");
        } else {
            for (int i = 0; i < taskList.numOfTasks(); i++) {
                ui.showMessage((i + 1) + ". " + taskList.getTask(i));
            }
        }
        ui.separate();
    }
}
