package lolo.command;

import lolo.Ui;
import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.TaskList;

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}