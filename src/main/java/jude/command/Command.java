package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;
import jude.Ui;

public abstract class Command {

    private boolean isExit;
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws JudeException;

    public void exit() {
        this.isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }
}
