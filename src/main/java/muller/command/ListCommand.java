package muller.command;

import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
