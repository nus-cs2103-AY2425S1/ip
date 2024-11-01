package darkpool.command;

import darkpool.gui.Gui;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) {
        return gui.list(taskList);
    }

}
