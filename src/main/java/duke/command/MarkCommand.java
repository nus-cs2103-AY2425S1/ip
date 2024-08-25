package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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