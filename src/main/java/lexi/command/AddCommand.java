package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        tasks.addTask(task);
        storage.updateStorage(tasks.getTasks());
        int taskSize = tasks.getSize();
        ui.showAddMessage(task, taskSize);
    }
    @Override
    public String getCommandName() {
        return "ADD";
    }
}
