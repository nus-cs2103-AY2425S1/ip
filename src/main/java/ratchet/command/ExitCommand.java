package ratchet.command;

import ratchet.storage.Storage;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Command to exit Ratchet.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.exit();
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
