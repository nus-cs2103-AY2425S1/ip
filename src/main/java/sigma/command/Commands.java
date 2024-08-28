package sigma.command;

import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;
import sigma.exception.SigmaException;

public abstract class Commands {

    protected String[] split;
    public Commands(String[] split) {
        this.split = split;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
