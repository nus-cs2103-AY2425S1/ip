package nayana.command;

import nayana.Storage;
import nayana.TaskList;
import nayana.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExit(); // Displays a confirmation message with the current task list
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

