package evan.command;

import evan.exception.NoSuchTaskException;
import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;

public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        taskList.markTaskAsDone(taskNumber);
        ui.showSuccess("Alright, I've marked task (" + taskNumber + ") as completed.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
