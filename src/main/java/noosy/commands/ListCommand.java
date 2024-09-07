package noosy.commands;

import noosy.storage.Storage;
import noosy.ui.Ui;
import noosy.task.TaskList;
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
