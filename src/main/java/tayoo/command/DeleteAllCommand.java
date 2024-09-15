package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

/**
 * This class contains all the commands to be executed when sending a delete all command
 */
public class DeleteAllCommand extends Command {

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        if (tasklist.deleteAll()) {
            ui.printText("Deleting all tasks");
        } else {
            ui.printText("No tasks to delete");
        }
    }

    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        if (tasklist.deleteAll()) {
            return "Deleting all tasks";
        } else {
            return "No tasks to delete";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
