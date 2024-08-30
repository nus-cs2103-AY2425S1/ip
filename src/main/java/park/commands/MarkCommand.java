package main.java.park.commands;

import main.java.park.exceptions.ParkException;
import main.java.park.storage.Storage;
import main.java.park.storage.TaskList;
import main.java.park.task.Task;
import main.java.park.ui.Ui;

public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        try {
            Task t = tasks.get(index);
            String oldLine = t.encode();
            t.mark();
            String newLine = t.encode();
            storage.modify(oldLine, newLine);
            ui.showToUser("OK, I've marked this task as done:" + t);
        } catch (IndexOutOfBoundsException e) {
            throw new ParkException("invalid index");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
