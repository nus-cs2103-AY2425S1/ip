package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException;
    public boolean isExit() {
        return false;
    }
}
