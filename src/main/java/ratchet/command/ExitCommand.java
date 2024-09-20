package ratchet.command;

import ratchet.storage.Storage;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Command to exit Ratchet.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        storage.saveTasks(tasks);
        return ui.exit();
    }
}
