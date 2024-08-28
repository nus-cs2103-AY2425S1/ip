package evan.command;

import evan.exception.NoSuchTaskException;
import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;

public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        taskList.deleteTask(taskNumber);
        ui.showSuccess("Okay, task (" + taskNumber + ") has been deleted.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
