package taskon.commands;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}