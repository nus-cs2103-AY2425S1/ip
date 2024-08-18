package commands;

import common.Command;
import common.SkibidiException;
import common.Ui;
import storage.Task;
import storage.TaskStorage;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(String task) {
        this.task = new Task(task);
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) throws SkibidiException {
        storage.addTask(task);
        ui.printMessage("added: " + task);
        return true;
    }
}