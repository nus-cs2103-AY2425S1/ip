package command;

import java.io.IOException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

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