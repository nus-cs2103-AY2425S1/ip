package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;
import jude.Ui;

/**
 * Represents the series of action the program has to execute, which is implemented in the execute method.
 */
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
