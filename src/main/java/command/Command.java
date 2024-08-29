package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList tasks, Ui ui);
    public abstract boolean isExit();
}
