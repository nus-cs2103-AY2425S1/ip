package darkpool.command;

import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.util.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.list(taskList);
    }

}
