package wolfie.command;

import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
