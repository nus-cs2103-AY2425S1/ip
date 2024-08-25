package command;

import java.io.IOException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class AddCommand implements Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getSize());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}