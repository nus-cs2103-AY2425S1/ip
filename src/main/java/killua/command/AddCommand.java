package killua.command;

import killua.task.Task;
import killua.util.KilluaException;
import killua.util.Storage;
import killua.util.TaskList;
import killua.util.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTasks().size());
        storage.save(tasks);
    }
}

