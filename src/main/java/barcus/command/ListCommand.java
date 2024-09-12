package barcus.command;

import barcus.storage.Storage;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

/**
 * Command to list out all tasks
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.talk("Okie, here are your tasks!");
        tasks.showTaskList();
        output = "Okie, here are your tasks!\n" + tasks.getTaskListDisplay();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
