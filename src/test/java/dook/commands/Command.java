package dook.commands;

import java.io.IOException;

import dook.DookException;
import dook.storage.Storage;
import dook.tasks.TaskList;
import dook.ui.Ui;


public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DookException, IOException;

    public boolean isExit() {
        return this.isExit;
    }
}
