package Tayoo.Command;

import Tayoo.Storage;
import Tayoo.Tasklist;
import Tayoo.Ui;
import Tayoo.Exception.TayooException;

public abstract class Command {

    public abstract void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException;
    public abstract boolean isExit();

}
