package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

public class UnmarkCommand extends Command {
    int selection;

    public UnmarkCommand(int selection) {
        this.selection = selection;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws AsuraException {
        tasklist.unMark(selection);
        storage.save(tasklist.getTaskList());
        output.append("OK, I've marked this task as not done yet:").append("\n").append(tasklist.get(selection).toString());
        ui.printString(output.toString());
    }

    public boolean isExit() {
        return false;
    }
}
