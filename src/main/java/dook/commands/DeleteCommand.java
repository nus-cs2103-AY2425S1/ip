package dook.commands;

import java.io.IOException;

import dook.DookException;
import dook.storage.Storage;
import dook.tasks.Task;
import dook.tasks.TaskList;
import dook.ui.Ui;

/**
 * The command to delete a task from the TaskList.
 */
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
        if (tasks.numOfTasks() == 1) {
            ui.showMessage("Now you have " + tasks.numOfTasks() + " task in the list");
        } else {
            ui.showMessage("Now you have " + tasks.numOfTasks() + " tasks in the list");
        }
        ui.separate();
    }
}
