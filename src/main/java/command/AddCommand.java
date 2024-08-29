package command;

import task.TaskList;
import task.Task;
import exception.ScheduloException;
import util.Storage;
import util.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ScheduloException, IOException {
        tasks.add(task);
        storage.save(tasks);
    }
}
