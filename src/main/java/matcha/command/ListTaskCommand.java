package matcha.command;

import matcha.tasklist.TaskList;

import matcha.storage.Storage;

import matcha.ui.Ui;

public class ListTaskCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
