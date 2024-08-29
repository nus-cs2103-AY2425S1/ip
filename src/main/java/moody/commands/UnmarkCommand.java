package moody.commands;

import moody.storage.Storage;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

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
