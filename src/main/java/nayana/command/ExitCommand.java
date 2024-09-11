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
        assert ui != null;
        ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

