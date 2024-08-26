package Dook.Commands;

import Dook.DookException;
import Dook.Storage.Storage;
import Dook.Tasks.TaskList;
import Dook.Ui.Ui;

import java.io.IOException;

public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DookException, IOException;

    public boolean isExit() {
        return this.isExit;
    }
}
