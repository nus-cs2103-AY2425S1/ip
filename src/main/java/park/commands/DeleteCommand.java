package main.java.park.commands;

import main.java.park.exceptions.ParkException;
import main.java.park.storage.Storage;
import main.java.park.storage.TaskList;
import main.java.park.task.Task;
import main.java.park.ui.Ui;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        try {
            Task t = tasks.get(index);
            storage.delete(t);
            ui.showToUser("Noted. I've removed this task:" + t);
            tasks.delete(index);
            ui.showToUser("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new ParkException("invalid index");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
