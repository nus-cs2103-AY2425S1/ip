package susan.command;

import susan.ui.Storage;
import susan.ui.SusanException;
import susan.task.TaskList;
import susan.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SusanException, IOException;

    public boolean isExit() {
        return false;
    }
}