package darkpool.command;

import darkpool.gui.Gui;
import darkpool.util.Storage;
import darkpool.tasklist.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) {
        return gui.list(taskList);
    }

}
