package Commands;

import Main.TaskList;
import UI.Ui;
import Tasks.Task;
import Storage.Storage;

public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskNumber < 1 || this.taskNumber > taskList.size()) {
            ui.showError("OI WRONG NUMBER.");
        } else {
            Task deleted = taskList.get(this.taskNumber - 1);
            taskList.deleteTask(this.taskNumber - 1);
            int numTasks = taskList.size();

            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage("  " + deleted.toString());
            ui.showMessage("Now you have " + numTasks + " tasks in the list");
            storage.saveTasks(taskList.getTasks());
        }
    }
}