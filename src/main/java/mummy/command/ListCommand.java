package mummy.command;

import mummy.task.TaskList;
import mummy.ui.Ui;
import mummy.utility.Storage;

/**
 * Represents a command to list all tasks in the task list.
 * When executed, it displays the string representation of the task list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show(taskList.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
