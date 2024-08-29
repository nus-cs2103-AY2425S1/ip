package nayana.command;

import nayana.TaskList;
import nayana.Ui;
import nayana.Storage;


/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

