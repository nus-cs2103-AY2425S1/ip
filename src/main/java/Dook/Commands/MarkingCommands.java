package Dook.Commands;

import Dook.Tasks.TaskList;
import Dook.Tasks.Task;
import Dook.Storage.Storage;
import Dook.Ui.Ui;
import Dook.DookException;

import java.io.IOException;

public class MarkingCommands extends Command{

    private int taskNumber;
    private boolean isMarked;

    public MarkingCommands(int taskNumber, boolean isMarked) {
        this.taskNumber = taskNumber;
        this.isMarked = isMarked;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DookException, IOException {
        Task task = taskList.getTask(taskNumber - 1);
        ui.separate();
        if (this.isMarked) {
            task.markAsDone();
            ui.showMessage("Nice! I've marked this task as done:");
        } else {
            task.unmark();
            ui.showMessage("Ok, I've marked this task as not done yet:");
        }
        storage.write(taskList);
        ui.showMessage(task.toString());
        ui.separate();
    }
}
