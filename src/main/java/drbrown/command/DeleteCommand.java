package drbrown.command;

import drbrown.task.Task;
import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DeleteCommand extends Command {

    private int itemIndex;

    public DeleteCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        try {
            ArrayList<Task> list = tasks.getList();
            Task deleteTask = list.get(this.itemIndex);
            tasks.removeItem(this.itemIndex);
            ui.showDeleteTask(deleteTask);
            ui.showEnd();
            ui.showCount(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException("You got the count wrong! That’s not how you calculate time travel – you're off by a few gigawatts!");
        }
    }

    public boolean isExit() {
        return false;
    }

}
