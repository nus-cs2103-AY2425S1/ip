package Tayoo.Command;

import Tayoo.Storage;
import Tayoo.Tasklist;
import Tayoo.Ui;
import Tayoo.Exception.TayooException;

public class ListCommand extends Command {

    public ListCommand() {
    }
    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        ui.printText(tasklist.printTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
