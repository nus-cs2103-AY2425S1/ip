package elara.command;

import elara.storage.Storage;
import elara.task.TaskList;
import elara.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listTasks(taskList);
    }
}
