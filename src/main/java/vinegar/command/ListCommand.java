package vinegar.command;

import vinegar.TaskList;
import vinegar.command.Command;
import vinegar.storage.Storage;
import vinegar.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
