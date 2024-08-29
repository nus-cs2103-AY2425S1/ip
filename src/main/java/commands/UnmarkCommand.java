package commands;

import commands.Command;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        storage.save(tasks.toArrayList());
        ui.showUnmarkedTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
