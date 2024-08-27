package Commands;

import Main.TaskList;
import UI.Ui;
import Tasks.Task;
import Storage.Storage;

public class UnmarkCommand extends Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskNumber < 1 || this.taskNumber > taskList.size()) {
            ui.showError("OI WRONG NUMBER.");
        } else {
            Task unmarked = taskList.get(this.taskNumber - 1);
            unmarked.markAsUndone();

            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage(unmarked.toString());
            storage.saveTasks(taskList.getTasks());
        }
    }
}