package toothless.command;

import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.printTask();
    }
}
