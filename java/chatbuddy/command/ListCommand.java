package chatbuddy.command;

import chatbuddy.storage.Storage;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
