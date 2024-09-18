package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

/**
 * Represents a command issued by user.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}

