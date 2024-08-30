package sage.command;

import sage.ui.Ui;
import sage.storage.Storage;
import sage.task.TaskList;

/**
 * Represents a command to exit the application
 * This command triggers the termination of the program
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
