package commands;

import common.Command;
import common.SkibidiException;
import common.Ui;
import storage.Task;
import storage.TaskStorage;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(String task) {
        this.task = new Task(task);
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        try {
            storage.addTask(task);
            ui.printMessage("added: " + task);
        } catch (SkibidiException | IOException e) {
            ui.printMessage(e.getMessage());
        }
        return true;
    }
}