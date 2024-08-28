package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the application.
 * This command signals the application to terminate, with no additional actions required.
 */
public class ExitCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No special actions needed for exit
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
