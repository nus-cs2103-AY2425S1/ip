package Majima.command;

import Majima.task.TaskList;
import Majima.ui.Ui;
import Majima.storage.Storage;

/**
 * When a ListCommand executes, it will print out all tasks to the console.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
