package morgana.commands;

import morgana.storage.Storage;
import morgana.task.TaskList;
import morgana.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
