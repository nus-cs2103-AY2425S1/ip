package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage, String argument);
}
