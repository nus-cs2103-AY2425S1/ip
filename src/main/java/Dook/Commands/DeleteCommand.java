package Dook.Commands;

import Dook.Tasks.TaskList;
import Dook.Tasks.Task;
import Dook.Storage.Storage;
import Dook.Ui.Ui;
import Dook.DookException;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {
        Task removed = tasks.delete(taskNumber - 1);
        storage.write(tasks);

        ui.separate();
        ui.showMessage("Noted. I've removed this task");
        ui.showMessage(removed.toString());
        ui.showMessage("Now you have " + tasks.numOfTasks() + " tasks in the list");
        ui.separate();
    }
}
