package mittens.commands;

import mittens.storage.Storage;
import mittens.task.TaskList;
import mittens.ui.Ui;

public abstract class Command {
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
