package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

/**
 * This is an abstract class that encapsulates what a command should be.
 */
public abstract class Command {

    public abstract void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException;
    public abstract String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException;
    public abstract boolean isExit();

}
