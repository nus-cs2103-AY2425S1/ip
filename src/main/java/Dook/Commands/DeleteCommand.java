package Dook.Commands;

import Dook.Tasks.TaskList;
import Dook.Tasks.Task;
import Dook.Storage.Storage;
import Dook.Ui.Ui;
import Dook.DookException;

import java.io.IOException;

public class DeleteCommand extends Command{

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DookException, IOException {
        Task removed = taskList.delete(taskNumber - 1);
        storage.write(taskList);

        ui.separate();
        ui.showMessage("Noted. I've removed this task");
        ui.showMessage(removed.toString());
        ui.showMessage("Now you have " + taskList.numOfTasks() + " tasks in the list");
        ui.separate();
    }
}
