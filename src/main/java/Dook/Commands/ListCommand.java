package Dook.Commands;

import Dook.Tasks.TaskList;
import Dook.Storage.Storage;
import Dook.Ui.Ui;
import Dook.DookException;

import java.io.IOException;

/**
 * The command that lists all the tasks in the TaskList.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {
        ui.separate();
        if (tasks.isEmpty()) {
            throw new DookException("No tasks");
        } else {
            for (int i = 0; i < tasks.numOfTasks(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i));
            }
        }
        ui.separate();
    }
}
