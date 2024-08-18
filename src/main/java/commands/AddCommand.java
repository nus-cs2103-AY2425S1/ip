package commands;

import common.Command;
import common.Ui;
import storage.TaskStorage;

public class AddCommand extends Command {
    private String task;

    public AddCommand(String task) {
        this.task = task;
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        storage.addTask(task);
        ui.printMessage("added: " + task);
        return true;
    }
}