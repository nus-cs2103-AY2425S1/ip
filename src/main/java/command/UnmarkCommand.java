package command;

import java.io.IOException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand implements Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.unmarkTaskAsDone(taskNumber);
        ui.showTaskUnmarked(tasks.getTask(taskNumber));
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}