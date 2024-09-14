package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

public class ListCommand extends Command {

    public ListCommand() {
        System.out.println("Created new list command");
    }
    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        ui.printText(tasklist.printTaskList());
    }

    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        System.out.println("Reached guiExecute");
        return tasklist.printTaskList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
