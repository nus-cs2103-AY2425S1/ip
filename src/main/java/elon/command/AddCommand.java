package elon.command;

import elon.Elon;
import elon.ElonException;
import elon.Storage;
import elon.Ui;
import elon.task.Deadline;
import elon.task.Event;
import elon.task.Task;
import elon.task.TaskList;
import elon.task.ToDo;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        list.addTask(this.task);
        storage.saveFile(list);
        return ui.addTask(task, list);
    }
}
