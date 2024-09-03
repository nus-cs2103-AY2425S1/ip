package vinegar.command;

import vinegar.TaskList;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException;
    public boolean isExit() {
        return false;
    }
}
