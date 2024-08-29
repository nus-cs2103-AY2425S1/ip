package commands;

import exceptions.AtlasException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    private boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AtlasException;

    public boolean isExit() {
        return this.isExit;
    }

    public void setIsExit() {
        this.isExit = true;
    }
}