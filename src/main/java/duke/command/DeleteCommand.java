package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand implements Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task removedTask = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        ui.showTaskRemoved(removedTask, tasks.getSize());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}