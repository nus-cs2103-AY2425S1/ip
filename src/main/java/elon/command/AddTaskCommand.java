package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.Task;
import elon.task.TaskList;

import java.io.IOException;

public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        list.addTask(this.task);
        storage.saveFile(list);
        return ui.addTask(task, list);
    }
}
