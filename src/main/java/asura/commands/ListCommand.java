package asura.commands;

import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        output.append("Here are the tasks in your list:\n");
        output.append(tasklist.toString());
        ui.printString(output.toString());
    }

    public boolean isExit() {
        return false;
    }
}
