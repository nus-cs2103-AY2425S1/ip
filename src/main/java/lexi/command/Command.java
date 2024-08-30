package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.TaskList;
import lexi.ui.Ui;

public abstract class Command {
    private boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException;
    public abstract String getCommandName();
    public void setExit() {
        this.isExit = true;
    }
    public boolean isExit() {
        return this.isExit;
    }

}
