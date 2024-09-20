package commands;

import exceptions.HandsomeException;
import storage.Storage;

import task.Task;
import task.TaskList;

import ui.Ui;

import java.io.IOException;
import java.util.Stack;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Stack<Command> commandRecords) throws IOException {
        String addedTask = tasks.addTask(task);
        storage.save(tasks.getTasks());
        return addedTask;
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws HandsomeException, IOException {
        int taskNum = tasks.getTasks().size() - 1;
        String completed = tasks.deleteCheck(taskNum);
        storage.save(tasks.getTasks());
        return completed;
    }
}
