package asura.commands;

import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
    }

    public boolean isExit() {
        return true;
    }
}
