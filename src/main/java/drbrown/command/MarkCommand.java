package drbrown.command;

import drbrown.task.Task;
import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

import java.util.ArrayList;

public class MarkCommand extends Command {

    private final int itemIndex;

    public MarkCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        try {
            ArrayList<Task> list = tasks.getList();
            Task markTask = list.get(itemIndex);
            markTask.setStatus(true);
            ui.showMarkTask(markTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException("You got the count wrong! That’s not how you calculate time travel – you're off by a few gigawatts!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
