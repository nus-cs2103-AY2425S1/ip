package killua.command;

import killua.task.Task;
import killua.util.KilluaException;
import killua.util.Storage;
import killua.util.TaskList;
import killua.util.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        tasks.markTaskDone(taskIndex);
        Task task = tasks.getTasks().get(taskIndex);
        ui.showTaskMarked(task);
        storage.save(tasks);
    }
}
