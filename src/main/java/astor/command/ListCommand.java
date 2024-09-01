package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;

/**
 * Represents a command to list the tasks stored.
 *
 * When executed, displays all the store tasks loaded from the storage and taskList and sends to the ui.
 * This is not a terminal command.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showOutput("Here are the tasks in your list:" + taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
