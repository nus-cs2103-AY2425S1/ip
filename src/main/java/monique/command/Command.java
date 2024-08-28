package monique.command;

import monique.exception.MoniqueException;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MoniqueException;
    public abstract boolean isActive();
}
