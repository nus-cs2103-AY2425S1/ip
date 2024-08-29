package bob.commands;

import bob.storage.Storage;
import bob.data.TaskList;
import bob.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList task, Ui ui, Storage storage);

    public abstract boolean isExit();
}
