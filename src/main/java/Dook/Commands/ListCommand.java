package Dook.Commands;

import Dook.Tasks.TaskList;
import Dook.Storage.Storage;
import Dook.Ui.Ui;
import Dook.DookException;

import java.io.IOException;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DookException, IOException {
        ui.separate();
        if (taskList.isEmpty()) {
            throw new DookException("No tasks");
        } else {
            for (int i = 0; i < taskList.numOfTasks(); i++) {
                ui.showMessage((i + 1) + ". " + taskList.getTask(i));
            }
        }
        ui.separate();
    }
}
