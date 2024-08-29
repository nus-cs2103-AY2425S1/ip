package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    protected int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task removedTask = tasks.remove(taskIndex);
        storage.save(tasks.toArrayList());
        ui.showTaskRemoved(removedTask, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
