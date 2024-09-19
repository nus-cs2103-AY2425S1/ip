package bing.command;

import bing.storage.Storage;
import bing.task.Task;
import bing.task.TaskList;
import bing.ui.Ui;

import java.io.IOException;

public abstract class AddCommand implements Command {
    protected final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showTasks(tasks);

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("Unable to save task.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
