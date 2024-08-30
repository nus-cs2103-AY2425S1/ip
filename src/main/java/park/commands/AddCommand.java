package main.java.park.commands;

import main.java.park.exceptions.ParkException;
import main.java.park.storage.Storage;
import main.java.park.storage.TaskList;
import main.java.park.task.Task;
import main.java.park.ui.Ui;

public class AddCommand extends Command {

    private final Task t;

    public AddCommand(Task t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        tasks.add(t);
        storage.append(t);
        ui.showToUser("Got it. I've added this task:" + t);
        ui.showToUser("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
