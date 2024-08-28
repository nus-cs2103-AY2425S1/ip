package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;
import jude.Ui;
import jude.task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.addTask(task);
        ui.showMessage("jude.task.Task " + task + " has been added.");
        storage.save(list);
    }
}
