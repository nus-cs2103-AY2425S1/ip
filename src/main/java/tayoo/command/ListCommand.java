package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

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
