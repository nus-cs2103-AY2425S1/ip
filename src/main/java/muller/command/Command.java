package muller.command;

import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MullerException;

    public boolean isExit() {
        return false;
    }
}

