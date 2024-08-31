package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

public class MarkCommand extends Command {
    int selection;

    public MarkCommand(int selection) {
        this.selection = selection;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws AsuraException {
        tasklist.mark(selection);
        storage.save(tasklist.getTaskList());
        output.append("Nice! I've marked this task as done:").append("\n").append(tasklist.get(selection).toString());
        ui.printString(output.toString());
    }

    public boolean isExit() {
        return false;
    }
}
