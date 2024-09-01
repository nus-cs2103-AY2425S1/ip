package drbrown.command;

import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        ui.showEnd();
        ui.closeCommand();
        storage.update(tasks);
    }

    public boolean isExit() {
        return true;
    }
}
