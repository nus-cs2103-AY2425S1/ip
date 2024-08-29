package tudee.command;

import tudee.task.TaskList;
import tudee.ui.Ui;
import tudee.storage.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.get());
    }
}