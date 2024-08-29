package tudee.command;

import tudee.task.TaskList;
import tudee.ui.Ui;
import tudee.storage.Storage;
import tudee.TudeeException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException;
    public boolean isExit() {
        return false;
    }
}
