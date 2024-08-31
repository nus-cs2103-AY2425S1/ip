package Majima.command;

import Majima.task.TaskList;
import Majima.ui.Ui;
import Majima.storage.Storage;

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
