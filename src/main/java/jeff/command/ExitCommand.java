package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;

/**
 * Represents a command that triggers the termination of the program.
 * This command instructs the user interface to display an exit message and sets
 * the condition for the program loop to terminate.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
