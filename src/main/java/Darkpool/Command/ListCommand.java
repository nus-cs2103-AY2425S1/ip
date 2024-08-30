package Darkpool.Command;

import Darkpool.util.Storage;
import Darkpool.util.TaskList;
import Darkpool.util.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.list(taskList);
    }

}
