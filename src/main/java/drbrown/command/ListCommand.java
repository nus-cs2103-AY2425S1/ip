package drbrown.command;

import drbrown.task.Task;
import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        if (tasks.getCount() == 0) {
            throw new DrBrownException("Wait a minute, Doc! There's nothing here! We can't go anywhere until you add something to the list!");
        }
        ArrayList<Task> list = tasks.getList();
        ui.showList();
        int listCount = 1;
        for (Task item: list) {
            System.out.println(listCount + ". " + item.toString());
            listCount++;
        }

    }

    public boolean isExit() {
        return false;
    }

}
