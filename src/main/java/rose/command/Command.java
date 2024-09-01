package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return false;
    }
}

