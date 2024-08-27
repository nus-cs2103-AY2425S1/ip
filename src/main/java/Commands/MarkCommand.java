package Commands;

import Main.TaskList;
import UI.Ui;
import Tasks.Task;
import Storage.Storage;

public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskNumber < 1 || this.taskNumber > taskList.size()) {
            ui.showError("OI WRONG NUMBER.");
        } else {
            Task marked = taskList.get(this.taskNumber - 1);
            marked.markAsDone();

            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage(marked.toString());
            storage.saveTasks(taskList.getTasks());
        }
    }
}