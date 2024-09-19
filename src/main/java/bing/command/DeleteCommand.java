package bing.command;

import bing.storage.Storage;
import bing.task.Task;
import bing.task.TaskList;
import bing.ui.Ui;
import java.io.IOException;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        tasks.deleteTask(index);
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
