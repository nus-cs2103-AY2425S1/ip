package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print(taskList.toString());
    }
}
