package toothless.command;

import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) {
        return taskList.printTask();
    }
}
