package command;

import java.io.IOException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand implements Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.markTaskAsDone(taskNumber);
        ui.showTaskMarked(tasks.getTask(taskNumber));
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}