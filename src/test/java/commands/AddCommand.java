package commands;

import storage.Storage;

import task.Task;
import task.TaskList;

import ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    private final Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
    }
}
